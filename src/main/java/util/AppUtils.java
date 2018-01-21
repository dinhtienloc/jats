package util;

import config.GlobalConfiguration;
import constants.Message;
import exception.ConnectionNotFound;

import java.sql.Connection;

/**
 * Created by locdt on 1/21/2018.
 */
public class AppUtils {
    public static Connection checkConnection() {
        if (GlobalConfiguration.connection == null) {
            System.out.println(Message.CONNECTION_NOT_FOUND);
            System.exit(1);
        }

        return GlobalConfiguration.connection;

    }
}
