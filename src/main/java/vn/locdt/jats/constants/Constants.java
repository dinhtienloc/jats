package vn.locdt.jats.constants;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
        private static Map<String, DBType> lookup = new HashMap<>();
        static {
            for (DBType type : DBType.values()) lookup.put(type.getType(), type);
        }

        public String getType() {return this.type;}
        public String getDriver() {return this.driver;}
        public String toString() {
            return this.type;
        }

        public static DBType enumOf(String type) {
            return lookup.get(type);
        }

        public static String[] getTypes() {
            return Arrays.stream(DBType.values()).map(DBType::getType).toArray(String[]::new);
        }
    }


}
