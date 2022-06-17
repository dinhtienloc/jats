package vn.locdt.jats.util.common;

public class OsUtils {
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private static final boolean WINDOWS_OS = System.getProperty("os.name").toLowerCase().contains("windows");

    public static boolean isWindows() {
        return WINDOWS_OS;
    }
}
