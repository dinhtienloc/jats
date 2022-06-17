package vn.locdt.jats.util.common;

import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNDiffClient;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

public class SVNUtil {
    public static SVNClientManager createSVNClientManager() {
        DefaultSVNOptions options = SVNWCUtil.createDefaultOptions(true);
        return SVNClientManager.newInstance(options);
    }

    public static long update(SVNClientManager clientManager, String filePath) throws SVNException {
        SVNUpdateClient updateClient = clientManager.getUpdateClient();
        return updateClient.doUpdate(new File(filePath), SVNRevision.HEAD, SVNDepth.fromRecurse(true), false, false);
    }

    public static boolean isUpdated(String filePath) {
        try {
            SVNClientManager clientManager = SVNUtil.createSVNClientManager();
            long workingRevision = getWorkingRevision(clientManager, filePath);
            long headRevision = getHeadRevision(clientManager, filePath);
            return workingRevision == headRevision;
        } catch (SVNException e) {
            throw new RuntimeException(e);
        }
    }

    public static long getWorkingRevision(SVNClientManager clientManager, String filePath) throws SVNException {
        return clientManager.getWCClient().doInfo(new File(filePath), SVNRevision.WORKING).getRevision().getNumber();
    }

    public static long getHeadRevision(SVNClientManager clientManager, String filePath) throws SVNException {
        return clientManager.getWCClient().doInfo(new File(filePath), SVNRevision.HEAD).getRevision().getNumber();
    }

    public static void diffWorkingAndBaseRevision(String filePath, final List<String> addedLines, final List<String> removeLines) throws IOException {
        SVNClientManager clientManager = SVNUtil.createSVNClientManager();
        SVNDiffClient diffClient = clientManager.getDiffClient();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            diffClient.doDiff(new File(filePath), SVNRevision.WORKING, new File(filePath), SVNRevision.HEAD, SVNDepth.FILES, false, bos, null);
        } catch (SVNException e) {
            throw new RuntimeException(e);
        }
        String outputDiff = new String(bos.toByteArray());
        BufferedReader reader = new BufferedReader(new StringReader(outputDiff));
        String line = null;
        if (outputDiff.length() > 0) {
            reader.readLine();
            reader.readLine();
        }
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("---") || line.startsWith("+++") || line.startsWith("@@") || line.startsWith(" ")) {
                continue;
            } else if (line.startsWith("-")) {
                removeLines.add(line.substring(1));
            } else if (line.startsWith("+")) {
                addedLines.add(line.substring(1));
            }
        }
    }
}