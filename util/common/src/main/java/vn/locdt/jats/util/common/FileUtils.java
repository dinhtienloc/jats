package vn.locdt.jats.util.common;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.SystemUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileUtils {
    public static final String CONFIG_FILE_NAME = "syn.properties";
    public static final String CONFIG_FOLDER_PATH = FileUtils.path(FileUtils.getUserDir());

    public static String getUserDir() {
        String currentLocation = null;
        if (SystemUtils.IS_OS_LINUX) {
            currentLocation = Paths.get(".").toAbsolutePath().toString();
        } else if (SystemUtils.IS_OS_WINDOWS) {
            currentLocation = System.getProperty("user.dir");
        } else if (SystemUtils.IS_OS_MAC_OSX) {
            currentLocation = Paths.get(".").toAbsolutePath().toString();
        } else if (SystemUtils.IS_OS_MAC) {
            currentLocation = Paths.get(".").toAbsolutePath().toString();
        }
        return currentLocation;
    }

    public static String path(String... folders) {
        return new FilePath().add(folders).build();
    }

    public static void createFile(Path path) throws IOException {
        if (!Files.exists(path)) {
            if (path.getParent() != null)
                Files.createDirectories(path.getParent());
            Files.createFile(path);
        }
    }

    public static File createFile(String path) throws IOException {
        Path des = Paths.get(path);
        createFile(des);

        return new File(path);
    }

    public static File createFileFromResource(ClassLoader cl, String fileName, String savedPath) throws IOException {
        InputStream is = cl.getResourceAsStream(fileName);
        File newFile = createFile(savedPath);
        FileOutputStream out = new FileOutputStream(newFile);
        IOUtils.copy(is, out);
        out.close();
        is.close();
        return newFile;
    }

    private static Path findFileWithPath(String path) {
        Path start = Paths.get("");
        Path result = null;
        try {
            result = Files.walk(start)
                    .filter(p -> p.toString().endsWith(path))
                    .findFirst().orElse(null);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Path findFileWithPackageName(String packageName) {
        String path = packageName.replace(".", File.separator);
        return findFileWithPath(path);
    }

    public static class FilePath {
        List<String> folders;

        private FilePath() {
            this.folders = new ArrayList<>();
        }

        private FilePath add(String... folders) {
            this.folders.addAll(Arrays.asList(folders));
            return this;
        }

        private String build() {
            return this.folders.isEmpty() ? "" : String.join(File.separator, this.folders);
        }
    }

    public static String getJarFileLocation() {
        String jarPath = FileUtils.class.getProtectionDomain().getCodeSource().getLocation().toExternalForm();
        if (jarPath.contains("jar")) {
            String rootJarPath = jarPath.substring("jar:file:".length(), jarPath.indexOf("BOOT-INF") - 2);
            int lastSeperatorIndex = rootJarPath.lastIndexOf("\\");
            if (lastSeperatorIndex < 0) {
                lastSeperatorIndex = rootJarPath.lastIndexOf("/");
            }

            return lastSeperatorIndex >= 0 ? rootJarPath.substring(0, lastSeperatorIndex) : "";
        } else {
            return ".";
        }
    }
}
