package vn.locdt.jats.bundle.question;

import vn.locdt.jats.bundle.question.exception.ConsoleNotInitializeException;

import java.io.IOException;
import java.util.Arrays;



public class Main {
    public enum DBType {
        MYSQL("MySQL"),
        ORACLE("Oracle");
        private String type;

        DBType(String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
        }
    }

    public static String[] getDatabaseTypes() {
        return Arrays.stream(DBType.values()).map(DBType::getType).toArray(String[]::new);
    }

    public static void main(String[] args) {
        try {
            String value = JQuestion.select("What do you want?", "want", getDatabaseTypes()).getValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
