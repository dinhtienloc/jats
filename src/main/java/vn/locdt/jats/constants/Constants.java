package vn.locdt.jats.constants;

import java.util.Arrays;

/**
 * Created by locdt on 1/21/2018.
 */
public class Constants {
    public enum DBType {
        MYSQL("MySQL", "com.mysql.jdbc.Driver"),
        ORACLE("Oracle", "oracle.jdbc.driver.OracleDriver");
        private String type;
        private String driver;
        DBType(String type, String driver) {
            this.type = type;
            this.driver = driver;
        }
        public String getType() {return this.type;}
        public String getDriver() {return this.driver;}
        public String toString() {
            return this.type;
        }
    }

    public static String[] getDatabaseTypes() {
        return Arrays.stream(DBType.values()).map(DBType::getType).toArray(String[]::new);
    }
}
