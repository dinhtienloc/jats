package vn.locdt.jats.addon.entity.modeling.util;

import org.apache.commons.lang.WordUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by locdt on 2/9/2018.
 */
public class StringUtils {
    public static boolean isNullString(String str) {
        return str == null;
    }
    public static boolean isStringValid(String str) {
        return !isNullString(str) && str.length() > 0;
    }
    public static String getCanonicalNameFromPath(String path) {
        return path.replace('/', '.').replace('\\', '.');
    }

    public static boolean containPackageInvalidCharacters(String name) {
        return name.contains("-");
    }

    public static String convertSqlNameToClassName(String name) {
        if (name == null || name.length() == 0) return "";
        return WordUtils.capitalizeFully(name, new char[]{'_'}).replace("_", "");
    }

    public static String convertCapitalizeFullyToVariableName(String name) {
        if (name.length() == 0)
            return "";

        char[] charArr = name.toCharArray();
        charArr[0] = Character.toLowerCase(charArr[0]);
        return new String(charArr);
    }

    public static String[] getSimpleName(String canonicalName) {
        String[] result = new String[2];

        if (!isStringValid(canonicalName))
            return result;

        if (!canonicalName.contains("."))
            return result;

        if (canonicalName.endsWith(".") || canonicalName.startsWith("."))
            return result;

        String simpleName = canonicalName.substring(canonicalName.lastIndexOf(".") + 1, canonicalName.length());

        if (isStringValid(simpleName)) {
            result[0] = simpleName;
            if (canonicalName.contains("java.lang"))
                result[1] = simpleName;
            else
                result[1] = canonicalName;
        }

        return result;
    }
}
