package vn.locdt.jats.core.util;

import org.apache.commons.lang.SystemUtils;
import org.fusesource.jansi.Ansi;
import org.springframework.shell.support.logging.HandlerUtils;

import java.nio.file.Paths;
import java.util.logging.Logger;

import static org.fusesource.jansi.Ansi.ansi;

/**
 * Created by locdt on 1/21/2018.
 */
public class Utils {
    private static final Logger logger = HandlerUtils.getLogger(vn.locdt.jats.addon.entity.modeling.util.Utils.class);
    public final static boolean DEBUG_MODE = false;

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

    public static void printLog(Object message) {
        logger.info(ansi().fg(Ansi.Color.CYAN).a("> " + message).fg(Ansi.Color.DEFAULT).toString());
    }

    public static void printSuccessLog(Object message) {
        logger.info(ansi().fg(Ansi.Color.YELLOW).a("[SUCCESS] " + message).fg(Ansi.Color.DEFAULT).toString());
    }

    public static void printErrorLog(Object result, Object cause) {
        logger.info(ansi().fg(Ansi.Color.RED).a("[ERROR] " + result)
                        .fg(Ansi.Color.DEFAULT).a(" (" + cause + ")").toString());
    }

    public static void printErrorLog(Object message) {
        logger.info(ansi().fg(Ansi.Color.RED).a("[ERROR] " + message)
                        .fg(Ansi.Color.DEFAULT).toString());
    }

    public static void printDebugLog(Object message) {
        if (!DEBUG_MODE) return;
        logger.warning(ansi().fg(Ansi.Color.MAGENTA).a("[DEBUG] ").fg(Ansi.Color.WHITE).a(message).fg(Ansi.Color.DEFAULT).toString());
    }
}
