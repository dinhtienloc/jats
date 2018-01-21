package config;

import constants.Constants;

import java.sql.Connection;

/**
 * Created by locdt on 1/21/2018.
 */
public class GlobalConfiguration {
    public static String dbType = Constants.DBType.MYSQL.getType();
    public static String dbUrl = "";
    public static String dbUser = "";
    public static String dbPass = "";

    public static String rootPackage = "";

    public static Connection connection = null;

    public static Constants.DBType getDatabaseType() {
        return Constants.DBType.valueOf(GlobalConfiguration.dbType);
    }
}
