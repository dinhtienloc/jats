package vn.locdt.jats.entity.addon.internal;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by locdt on 1/21/2018.
 */
public class DatabaseReader {
    private static final String CONNECTION_NOT_FOUND = "Connection not found!";
    private static final String ERROR_EXECUTING_SQL = "Error occurs when executing sql";

    private Connection conn;

    public DatabaseReader(Connection conn) {
        this.conn = conn;
    }

    public List<String> getDatabaseTableList(String catalogName) {
        if (conn == null) return null;

        List<String> tables = new ArrayList<>();
        try {
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getTables(catalogName, null, null, new String[]{"TABLE"});
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                tables.add(tableName);
            }
        } catch (SQLException e) {
            System.out.println(ERROR_EXECUTING_SQL);
        }

        return tables;
    }

    public List<String> getCatalogList() {
        if (conn == null) return null;

        List<String> catalogs = new ArrayList<>();
        try {
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getCatalogs();
            while (rs.next()) {
                String tableName = rs.getString("TABLE_CAT");
                catalogs.add(tableName);
            }
        } catch (SQLException e) {
            System.out.println(ERROR_EXECUTING_SQL);
        }
        return catalogs;
    }
}
