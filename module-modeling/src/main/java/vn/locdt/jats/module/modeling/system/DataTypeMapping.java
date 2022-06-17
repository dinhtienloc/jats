package vn.locdt.jats.module.modeling.system;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by locdt on 2/11/2018.
 */
public class DataTypeMapping {
    private static Map<Integer, String> dataMap = new HashMap<>();

    static {
        puts(dataMap, "java.lang.String", Types.CHAR, Types.VARCHAR, Types.NCHAR, Types.NVARCHAR, Types.LONGNVARCHAR, Types.LONGVARCHAR);
        puts(dataMap, "java.lang.Byte[]", Types.BINARY, Types.VARBINARY, Types.LONGVARBINARY);
        puts(dataMap, "java.lang.Boolean", Types.BIT);
        puts(dataMap, "java.lang.Short", Types.TINYINT, Types.SMALLINT);
        puts(dataMap, "java.lang.Integer", Types.INTEGER);
        puts(dataMap, "java.lang.Float", Types.REAL);
        puts(dataMap, "java.lang.Double", Types.DOUBLE, Types.FLOAT);
        puts(dataMap, "java.math.BigDecimal", Types.DECIMAL, Types.NUMERIC);
        puts(dataMap, "java.sql.Date", Types.DATE);
        puts(dataMap, "java.sql.Time", Types.TIME);
        puts(dataMap, "java.sql.Timestamp", Types.TIMESTAMP);
    }

    private static void puts(Map map, String value, Integer... keys) {
        for (Integer i : keys) map.put(i, value);
    }

    public static String getJavaType(Integer dataType) {
        return dataMap.get(dataType);
    }
}
