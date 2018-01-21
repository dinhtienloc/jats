package internal.database;

import constants.Message;
import util.AppUtils;

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

    public List<String> getDatabaseTableList() {
        List<String> tables = new ArrayList<>();
        Connection conn = AppUtils.checkConnection();
        try {
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getTables(null, null, null, new String[]{"TABLE"});
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                tables.add(tableName);
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println(Message.ERROR_EXECUTING_SQL);
        }

        return tables;
    }
}
