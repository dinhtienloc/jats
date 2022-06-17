package vn.locdt.jats.util.common;

import org.fusesource.jansi.Ansi;
import vn.locdt.jats.util.exception.ErrorLogWaitException;

import java.util.function.Supplier;

import static org.fusesource.jansi.Ansi.ansi;

public class LogUtils {
    private static boolean DEBUG_MODE = false;
    public static String SUCCESS = "SUCCESS";
    public static String ERROR = "ERROR";

    public static void enableDebug() {
        DEBUG_MODE = true;
        printLog("Enabled debug log");
    }

    public static void disableDebug() {
        DEBUG_MODE = false;
        printLog("Disabled debug log");
    }

    public static String bold(Object message) {
        return ansi().bold().a(message.toString()).boldOff().toString();
    }

    public static String createLog(Object message, LogType type, Object... params) {
        switch (type) {
            case SUCCESS:
                return createSuccessLog(message, params);
            case DEBUG:
                return createErrorLog(message, params);
            case ERROR:
                return createDebugLog(message, params);
            case NORMAL:
                return createLog(message, params);
            case WARNING:
                return createWarningLog(message, params);
        }
        return "";
    }

    public static String createLog(Object message, Object... params) {
        return ansi().fg(Ansi.Color.DEFAULT).a(bold("[LOG] " + createMessage(message, params))).toString();
    }

    public static String createSuccessLog(Object message, Object... params) {
        return ansi().fg(Ansi.Color.GREEN).a(createMessage(message, params))
                .fg(Ansi.Color.DEFAULT).toString();
    }

    private static String createWarningLog(Object message, Object... params) {
        return ansi().fg(Ansi.Color.YELLOW).a(createMessage(message, params))
                .fg(Ansi.Color.DEFAULT).toString();
    }

    private static String createErrorLog(Object result, Object cause, Object... params) {
        return ansi().fg(Ansi.Color.RED).a("[!] " + createMessage(result, params))
                .fg(Ansi.Color.DEFAULT).a(" (" + cause + ")").toString();
    }

    public static String createErrorLog(Object message, Object... params) {
        return ansi().fg(Ansi.Color.RED).a("[!] " + createMessage(message, params))
                .fg(Ansi.Color.DEFAULT).toString();
    }

    private static String createDebugLog(Object message, Object... params) {
        return ansi().fg(Ansi.Color.MAGENTA).a("[DEBUG] ")
                .fg(Ansi.Color.WHITE).a(createMessage(message, params))
                .fg(Ansi.Color.DEFAULT).toString();
    }

    public static String createLogWait(Object message, LogType type, Supplier<String> supplier, Object... params) throws ErrorLogWaitException {
        System.out.print(createLog(message, type, params));
        String mes = supplier.get();
        if (isError(mes)) {
            throw new ErrorLogWaitException();
        }
        return mes;
    }

    public static void printLog(Object message, Object... params) {
        System.out.println(createLog(message, params));
    }

    public static void printSuccessLog(Object message, Object... params) {
        System.out.println(createSuccessLog(message, params));
    }

    public static void printErrorLog(Object result, Throwable cause, Object... params) {
        System.out.println(createErrorLog(result, cause, params));
    }

    public static void printErrorLog(Object message, Object... params) {
        System.out.println(createErrorLog(message, params));
    }

    public static void printLogWait(Object message, LogType type, Supplier<String> supplier, Object... params) throws ErrorLogWaitException {
        System.out.println(createLogWait(message, type, supplier, params));
    }

    public static void printWarningLog(Object message, Object... params) {
        System.out.println(createWarningLog(message, params));
    }

    public static void printDebugLog(Object message, Object... params) {
        if (!DEBUG_MODE) return;
        System.out.println(createDebugLog(message, params));
    }

    private static String createMessage(Object message, Object... params) {
        return String.format(message.toString(), params);
    }

    public static boolean isError(String str) {
        return ERROR.equals(str);
    }
}
