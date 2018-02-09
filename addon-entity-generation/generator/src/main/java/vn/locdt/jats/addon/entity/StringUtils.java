package vn.locdt.jats.addon.entity;

import java.io.File;

/**
 * Created by locdt on 2/9/2018.
 */
public class StringUtils {
    public static String getCanonicalNameFromPath(String path) {
        return path.replace('/', '.').replace('\\', '.');
    }

    public static boolean containPackageInvalidCharacters(String name) {
        return name.contains("-");
    }
}
