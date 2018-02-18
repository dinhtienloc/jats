package vn.locdt.jats.util;

import org.apache.commons.lang.SystemUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FileUtils {
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

    public static String getConfigurationPath() {
        return "jats.properties";
    }

    public static Path findFileWithPath(String path) {
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
}
