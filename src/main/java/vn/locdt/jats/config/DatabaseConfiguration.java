package vn.locdt.jats.config;

import vn.locdt.jats.constants.Constants;
import vn.locdt.jats.constants.PropertiesConstants;
import vn.locdt.jats.util.Utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by locdt on 1/23/2018.
 */
public class DatabaseConfiguration extends Configuration {
    private String dbType;
    private String dbUrl;
    private String dbUser;
    private String dbPass;
    private Connection connection = null;

    public DatabaseConfiguration() {
        super();
    }

    public DatabaseConfiguration(Properties prop) {
        this.dbType = prop.getProperty(PropertiesConstants.DBTYPE);
        this.dbUrl = prop.getProperty(PropertiesConstants.DBURL);
        this.dbUser = prop.getProperty(PropertiesConstants.DBUSER);
        this.dbPass = prop.getProperty(PropertiesConstants.DBPASS);
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getDbPass() {
        return dbPass;
    }

    public void setDbPass(String dbPass) {
        this.dbPass = dbPass;
    }

    public void loadDriver(String type) {
        for (Constants.DBType dbType : Constants.DBType.values()) {
            try {
                if (dbType.getType().equals(type)) {
                    Class.forName(dbType.getDriver());
                    System.out.println(dbType.getType() + " Driver Loaded...");
                    this.setDbType(type);
                }
            } catch (ClassNotFoundException e) {
                System.out.println(dbType.getType() + " Driver Not Found...");
                System.exit(1);
            }
        }
    }

    public void createConnection() {
        System.out.print("Check connection...");
        try {
            this.connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            System.out.println("Connect successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Connect unsuccessfully!");
        }
    }

    public Connection getConnection()  {
        return connection;
    }
}
