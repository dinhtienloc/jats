package vn.locdt.jats.addon.entity.modeling.util;

import vn.locdt.jats.addon.entity.modeling.model.Catalog;
import vn.locdt.jats.addon.entity.modeling.model.Column;
import vn.locdt.jats.addon.entity.modeling.model.Table;

/**
 * Created by locdt on 2/2/2018.
 */
public class Utils {
    public static boolean isValidString(String str) {
        return str != null && str.length() > 0;
    }

    public static Table findTableByName(Catalog catalog, String name) {
        if (!Utils.isValidString(name)) return null;
        for (Table table : catalog.getTables())
            if (name.equals(table.getName())) return table;
        return null;
    }

    public static Column findColumnInTableByName(Catalog catalog, String tableName, String columnName) {
        if (columnName == null || tableName == null) return null;
        Table table = findTableByName(catalog, tableName);
        if (table == null) return null;

        for (Column col : table.getColumns()) {
            if (columnName.equals(col.getName()))
                return col;
        }
        return null;
    }
}
