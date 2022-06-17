package vn.locdt.jats.synergix.addon.db;

public enum DatabaseType {
    DB2("com.ibm.db2.jcc.DB2Driver"),
    POSTGRESQL("org.postgresql.Driver"),
    MYSQL("com.mysql.cj.jdbc.Driver");

    private final String driver;

    DatabaseType(String driver) {
        this.driver = driver;
    }

    public String getDriver() {
        return driver;
    }
}
