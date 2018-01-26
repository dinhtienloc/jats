package vn.locdt.jats.setting;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import vn.locdt.jats.constants.Constants;
import vn.locdt.jats.constants.PropertiesConstants;
import vn.locdt.jats.util.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by locdt on 1/23/2018.
 */
public class DatabaseSetting extends Setting {
    private static String dbType;
    private static String dbUrl;
    private static String dbUser;
    private static String dbPass;
    private static Connection connection = null;
    private static Configuration hbmConfiguration;

    public DatabaseSetting() {}

    public DatabaseSetting(Properties prop) {
        this.dbType = prop.getProperty(PropertiesConstants.DBTYPE);
        this.dbUrl = prop.getProperty(PropertiesConstants.DBURL);
        this.dbUser = prop.getProperty(PropertiesConstants.DBUSER);
        this.dbPass = prop.getProperty(PropertiesConstants.DBPASS);

        Utils.printDebugLog(this.hbmConfiguration);
        if (dbType != null && dbUrl != null && dbUser != null && dbPass != null) {
            this.hbmConfiguration = new Configuration();
            hbmConfiguration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            hbmConfiguration.setProperty("hibernate.connection.driver_class", Constants.DBType.enumOf(dbType).getDriver());
            hbmConfiguration.setProperty("hibernate.connection.url", dbUrl);
            hbmConfiguration.setProperty("hibernate.connection.username", dbUser);
            hbmConfiguration.setProperty("hibernate.connection.password", dbPass);

            // disable hibernate log
            hbmConfiguration.setProperty("hibernate.show_sql", "false");
            hbmConfiguration.setProperty("hibernate.generate_statistics", "false");
            hbmConfiguration.setProperty("hibernate.use_sql_comments", "false");
        }
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

    public Configuration getHbmConfiguration() {
        return hbmConfiguration;
    }

    public void setHbmConfiguration(Configuration hbmConfiguration) {
        DatabaseSetting.hbmConfiguration = hbmConfiguration;
    }

    public boolean loadDriver(String type) {
        for (Constants.DBType dbType : Constants.DBType.values()) {
            try {
                if (dbType.getType().equals(type)) {
                    Class.forName(dbType.getDriver());
                    Utils.printLog(dbType.getType() + " Driver Loaded...");
                    this.setDbType(type);
                    return true;
                }
            } catch (ClassNotFoundException e) {
                Utils.printErrorLog(dbType.getType() + " Driver Not Found...");
            }
        }
        return false;
    }

    public boolean createConnection(){
        try {
            Utils.printLog("Establish connection...");
            this.connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            Utils.printSuccessLog("Connecting successfully!");
            return true;
        } catch (Exception e) {
//            e.printStackTrace();
            Utils.printErrorLog("Connecting unsuccessfully", e.getMessage());
        }
        return false;
    }

    public Connection getConnection()  {
        return connection;
    }
}
