package vn.locdt.jats.module.modeling.util;

import vn.locdt.jats.module.modeling.model.Catalog;
import vn.locdt.jats.module.modeling.model.Column;
import vn.locdt.jats.module.modeling.model.Table;
import vn.locdt.jats.util.common.StringUtils;

/**
 * Created by locdt on 2/2/2018.
 */
public class DatabaseUtils {

    public static Table findTableByName(Catalog catalog, String name) {
        if (!StringUtils.isNotEmpty(name)) return null;
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
