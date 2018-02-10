package vn.locdt.jats.core.setting;

import vn.locdt.jats.core.constants.Constants;
import vn.locdt.jats.core.constants.PropertiesConstants;
import vn.locdt.jats.core.util.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
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

    public DatabaseSetting() {}

    public DatabaseSetting(Properties prop) {
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