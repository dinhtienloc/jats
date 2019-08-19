package vn.locdt.jats.synergix.addon.util;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import vn.locdt.jats.module.shell.context.ContextKey;
import vn.locdt.jats.module.shell.context.ShellRuntimeContext;
import vn.locdt.jats.synergix.addon.db.DatabaseInfo;
import vn.locdt.jats.synergix.addon.db.queryaction.QueryAction;
import vn.locdt.jats.util.common.*;
import vn.locdt.jats.util.exception.ErrorLogWaitException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CommonUtils {

	public static final String SYNERGIX_ID = "U999999999";

	public static void updateSuperModel(SVNClientManager svnClientManager) throws ErrorLogWaitException {
		final String superModelPath = ShellRuntimeContext.getContext(ContextKey.SUPERMODEL_PATH, String.class);
		LogUtils.printLogWait("Update SuperModel to latest rev...", LogType.SUCCESS, () -> {
			try {
				long headRev = SVNUtil.update(svnClientManager, superModelPath);
				return LogUtils.createLog("rev %s", LogType.SUCCESS, headRev);
			}
			catch (SVNException e) {
				e.printStackTrace();
				return LogUtils.ERROR;
			}
		});
	}

	public static void append01_form_master(String formCode, String createdBy, String formQuery) throws ErrorLogWaitException {
		final String superModelPath = ShellRuntimeContext.getContext(ContextKey.SUPERMODEL_PATH, String.class);
		LogUtils.printLogWait("Append form code query to " + LogUtils.bold("01_form_master.sql") + " file...", LogType.SUCCESS, () -> {
			try {
				File formMasterFile = new File(FileUtils.path(superModelPath, "data", "common", "01_form_master.sql"));
				String existFormCode = new Scanner(formMasterFile).findWithinHorizon(formCode, 0);
				if (StringUtils.isNotEmpty(existFormCode)) {
					return "Already Exist";
				}
				else {
					String dateStr = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
					String content = "\n--" + dateStr + " " +createdBy + "\n" + formQuery + ";\n" + "--";
					FileOutputStream fos = new FileOutputStream(formMasterFile, true);
					fos.write(content.getBytes());
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				return LogUtils.ERROR;
			}
			return LogUtils.SUCCESS;
		});
	}

	public static void assignRoleCodePermission(String formCode, String role, DatabaseInfo mainDbInfo) throws ErrorLogWaitException {
		String createdDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		final String roleQuery = String.format("INSERT INTO MT_ROLE_ACCESS (FORM_CODE, ROLE_CODE, CREATED_BY, CREATED_DATETIME, LAST_UPDATED_BY, LAST_UPDATED_DATETIME) " +
				"VALUES ('%s','%s','%s','%s','%s','%s')", formCode, role, SYNERGIX_ID, createdDateTime, SYNERGIX_ID, createdDateTime);

		LogUtils.printLogWait("Assign role " + LogUtils.bold(role) + " to " + LogUtils.bold(formCode) + "...", LogType.SUCCESS, () -> {
			Connection mainCon = mainDbInfo.getConnection();
			return insertData(mainCon, roleQuery);
		});
	}

	public static void assignDashpanePermission(String formCode, String role, DatabaseInfo mainDbInfo) throws ErrorLogWaitException {
		String createdDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

		final String roleQuery = String.format("INSERT INTO mt_role_dashpane_access (DASHPANE_CODE, ROLE_CODE, CREATED_BY, CREATED_DATETIME, LAST_UPDATED_BY, LAST_UPDATED_DATETIME)" +
				" VALUES ('%s','%s','%s','%s','%s','%s')", formCode, role, SYNERGIX_ID, createdDateTime, SYNERGIX_ID, createdDateTime);

		LogUtils.printLogWait("Assign role " + LogUtils.bold(role) + " to " + LogUtils.bold(formCode) + "...", LogType.SUCCESS, () -> {
			Connection mainCon = mainDbInfo.getConnection();
			return insertData(mainCon, roleQuery);
		});
	}

	public static String insertData(Connection con, String query) {
		try {
			QueryAction.create(con, query).execute();
		}
		catch (SQLException e) {
			if ("23505".equals(e.getSQLState())) {
				return "Already Exist";
			}
			else{
				System.out.println(e.getMessage());
				return LogUtils.ERROR;
			}
		}
		return LogUtils.SUCCESS;
	}
}
