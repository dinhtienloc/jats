package vn.locdt.jats.synergix.addon.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class DatabaseInfo {
    public final String name;
    public final DatabaseType type;
    public final String url;
    public final String user;
    public final String password;
    private Connection conn;
    private DatabaseGroup group;

    public DatabaseInfo(String alias, String type, String url, String user, String password) {
        this.name = alias;
        this.type = DatabaseType.valueOf(type);
        this.url = url;

        this.user = user;
        this.password = password;
    }

    public DatabaseGroup getGroup() {
        return this.group;
    }

    public void setGroup(DatabaseGroup group) {
        this.group = group;
    }

    public Connection getConnection() {
        try {
            if (this.conn != null && !this.conn.isClosed())
                return this.conn;

            Properties connectionProps = new Properties();
            connectionProps.put("user", this.user);
            connectionProps.put("password", this.password);


            this.conn = DriverManager.getConnection(this.url, connectionProps);
            if (this.conn == null) throw new IllegalStateException("invalid database configuration");
            return this.conn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection() {
        try {
            if (this.conn != null && !this.conn.isClosed()) {
                this.conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return this.name + " - " + this.url;
    }
}
