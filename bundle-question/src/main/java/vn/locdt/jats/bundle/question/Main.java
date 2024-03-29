package vn.locdt.jats.bundle.question;

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

//    public static void main(String[] args) {
//        try {
//            Terminal terminal = TerminalBuilder.builder().system(true).jansi(true).build();
//            LineReader lineReader = LineReaderBuilder.builder().terminal(terminal).build();
//
////            JQuestion.input("test:").getValue();
//            JQuestion.select(lineReader, "What do you want?", "want", Arrays.asList(getDatabaseTypes())).getValue();
////            String value = JQuestion.select("What do you want?", "want", getDatabaseTypes()).getValue();
////            System.out.println(JQuestion.autocompleteInput(lineReader, "test").getValue());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
