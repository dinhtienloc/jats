package vn.locdt.jats.config;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import vn.locdt.jats.constants.Constants;
import vn.locdt.jats.constants.PropertiesConstants;

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
    private static Configuration hbmConfiguration;

    public DatabaseSetting(Properties prop) {
        this.dbType = prop.getProperty(PropertiesConstants.DBTYPE);
        this.dbUrl = prop.getProperty(PropertiesConstants.DBURL);
        this.dbUser = prop.getProperty(PropertiesConstants.DBUSER);
        this.dbPass = prop.getProperty(PropertiesConstants.DBPASS);

        this.hbmConfiguration = new Configuration(new MetadataSources(
            new StandardServiceRegistryBuilder()
                .applySetting("hibernate.connection.driver_class", Constants.DBType.valueOf(dbType).getDriver())
                .applySetting("hibernate.connection.url", dbUrl)
                .applySetting("hibernate.connection.username", dbUser)
                .applySetting("hibernate.connection.password", dbPass)
                .build()));
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