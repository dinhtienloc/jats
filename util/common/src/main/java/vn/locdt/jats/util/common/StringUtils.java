package vn.locdt.jats.util.common;

import org.apache.commons.lang.WordUtils;

public class StringUtils {
	public static boolean containPackageInvalidCharacters(String name) {
		return name.contains("-");
	}

	public static boolean isNullString(String str) {
		return str == null;
	}
	public static boolean isNotEmpty(String str) {
		return !isNullString(str) && str.length() > 0;
	}

	public static boolean isEmpty(String str) {
		return !isNotEmpty(str);
	}

	public static String getCanonicalNameFromPath(String path) {
		if (StringUtils.isNotEmpty(path))
			return path.replace('/', '.').replace('\\', '.');
		return "";
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
		String varName = new String(charArr);
		if ("class".equals(varName))
			return "clazz";
		return varName;
	}

	public static String[] getSimpleName(String canonicalName) {
		String[] result = new String[2];

		if (!isNotEmpty(canonicalName))
			return result;

		if (!canonicalName.contains("."))
			return result;

		if (canonicalName.endsWith(".") || canonicalName.startsWith("."))
			return result;

		String simpleName = canonicalName.substring(canonicalName.lastIndexOf(".") + 1, canonicalName.length());

		if (isNotEmpty(simpleName)) {
			result[0] = simpleName;
			if (canonicalName.contains("java.lang"))
				result[1] = simpleName;
			else
				result[1] = canonicalName;
		}

		return result;
	}
}
