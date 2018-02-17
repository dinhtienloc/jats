package vn.locdt.jats.util;

import org.fusesource.jansi.Ansi;
import org.springframework.shell.support.logging.HandlerUtils;

import java.util.logging.Logger;

import static org.fusesource.jansi.Ansi.ansi;

public class LogUtils {
    private static final Logger logger = HandlerUtils.getLogger(LogUtils.class);
    public final static boolean DEBUG_MODE = true;

    public static String createLog(Object message) {
        return ansi().fg(Ansi.Color.CYAN).a(message)
                .fg(Ansi.Color.DEFAULT).toString();
    }

    public static String createSuccessLog(Object message) {
        return ansi().fg(Ansi.Color.GREEN).a(message)
                .fg(Ansi.Color.DEFAULT).toString();
    }

    public static String createWarningLog(Object message) {
        return ansi().fg(Ansi.Color.YELLOW).a(message).toString();
    }

    public static String createErrorLog(Object result, Object cause) {
        return ansi().fg(Ansi.Color.RED).a("[!] " + result)
                .fg(Ansi.Color.DEFAULT).a(" (" + cause + ")").toString();
    }

    public static String createErrorLog(Object message) {
        return ansi().fg(Ansi.Color.RED).a("[!] " + message)
                .fg(Ansi.Color.DEFAULT).toString();
    }

    public static String createDebugLog(Object message) {
        return ansi().fg(Ansi.Color.MAGENTA).a("[DEBUG] ")
                .fg(Ansi.Color.WHITE).a(message)
                .fg(Ansi.Color.DEFAULT).toString();
    }

    public static void printLog(Object message) {
        logger.info(createLog(message));
    }

    public static void printSuccessLog(Object message) {
        logger.info(createSuccessLog(message));
    }

    public static void printErrorLog(Object result, Object cause) {
        logger.info(createErrorLog(result, cause));
    }

    public static void printErrorLog(Object message) {
        logger.info(createErrorLog(message));
    }

    public static void printDebugLog(Object message) {
        if (!DEBUG_MODE) return;
        logger.warning(createDebugLog(message));
    }
}
