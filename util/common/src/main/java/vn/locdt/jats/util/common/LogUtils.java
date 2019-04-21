package vn.locdt.jats.util.common;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import org.springframework.shell.Utils;

import java.util.logging.Logger;

import static org.fusesource.jansi.Ansi.ansi;

public class LogUtils {
    private static boolean DEBUG_MODE = false;

    public static void enableDebug() {
    	DEBUG_MODE = true;
    	printLog("Enabled debug log");
    }

    public static void disableDebug() {
    	DEBUG_MODE = false;
	    printLog("Disabled debug log");
    }

    private static String createLog(Object message, Object... params) {
        return ansi().fg(Ansi.Color.CYAN).a(createMessage(message, params))
                .fg(Ansi.Color.DEFAULT).toString();
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
}
