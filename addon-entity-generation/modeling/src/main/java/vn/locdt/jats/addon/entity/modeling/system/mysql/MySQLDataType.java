package vn.locdt.jats.addon.entity.modeling.system.mysql;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by locdt on 2/3/2018.
 */
public enum MySQLDataType {
    BIT(Types.BIT, "Bit"),
    TINYINT(Types.TINYINT, "TINYINT");

    private int type;
    private String name;
    MySQLDataType(int type, String name) {
        this.type = type;
        this.name = name;
    }

    private static Map<Integer, MySQLDataType> lookup = new HashMap<>();
    static {
        for (MySQLDataType e : MySQLDataType.values()) lookup.put(e.getType(), e);
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public static MySQLDataType enumOf(int type) {
        return lookup.get(type);
    }
}
