package vn.locdt.jats.util.common;

import org.fusesource.jansi.Ansi;
import org.springframework.shell.Utils;

import java.util.logging.Logger;

import static org.fusesource.jansi.Ansi.ansi;

public class LogUtils {
    public static boolean DEBUG_MODE = false;

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
        System.out.println(createLog(message));
    }

    public static void printSuccessLog(Object message) {
        System.out.println(createSuccessLog(message));
    }

    public static void printErrorLog(Object result, Object cause) {
        System.out.println(createErrorLog(result, cause));
    }

    public static void printErrorLog(Object message) {
        System.out.println(createErrorLog(message));
    }

    public static void printWarningLog(Object message) {
        System.out.println(createWarningLog(message));
    }
    public static void printDebugLog(Object message) {
        if (!DEBUG_MODE) return;
        System.out.println(createDebugLog(message));
    }
}
