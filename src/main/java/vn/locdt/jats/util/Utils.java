package vn.locdt.jats.util;

import org.apache.commons.lang.SystemUtils;

import java.nio.file.Paths;

/**
 * Created by locdt on 1/21/2018.
 */
public class Utils {
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
        return getUserDir() + "\\jats.properties";
    }
}
