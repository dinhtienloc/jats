package vn.locdt.jats.synergix.addon.config;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by locdt on 2/11/2018.
 */
public class SynergixDataType {
    private static Map<Integer, String> dataMap = new HashMap<>();
    static {
        puts(dataMap, "desc", "description");
	    puts(dataMap, "name", "name");
        puts(dataMap, "number", "double", "currency-amt", "quantity", "unit-price", "integer", "long");
        puts(dataMap, "code", "code");
        puts(dataMap, "date", "date");
        puts(dataMap, "datetime", "time", "timestamp");
	    puts(dataMap, "percent", "percentage");
	    puts(dataMap, "datetime", "time", "timestamp");

    }

    private static void puts(Map map, String styleClass, String... dataTypes) {
        for (String type : dataTypes) map.put(type, styleClass);
    }

    public static String getStyleClass(String dataType) {
        return dataMap.get(dataType);
    }
}
