package vn.locdt.jats.core.question;

import java.util.Arrays;



public class Main {
    public enum DBType {
        MYSQL("MySQL"),
        ORACLE("Oracle");
        private String type;
        DBType(String type) {
            this.type = type;
        }
        public String getType() {return this.type;}
    }

    public static String[] getDatabaseTypes() {
        return Arrays.stream(DBType.values()).map(DBType::getType).toArray(String[]::new);
    }

    public static void main(String[] args) {
//        try {
            String value = JQuestion.select("What do you want?", "want", getDatabaseTypes()).getValue();
//            Map<String, String> resultMap = jQuestion.createQuestionGroup()
//                .addInputQuestion("What is your grade?", "group")
//                .addSingleChoiceQuestion(new SingleChoiceQuestion("What do you want?", "want")
//                        .addSelectors("Candy", "Apple", "Nothing"))
//                .prompt();

//            Gson gson = new Gson();
//            System.out.println(gson.toJson(resultMap));

//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ConsoleNotInitializeException e) {
//            e.printStackTrace();
//        }
    }
}
////    public static void main(String[] args) {
////        try {
////            System.out.println("Test Console Reader");
////            ConsoleReader console = new ConsoleReader();
////            int key = 0;
////            while(key != 3) {
////                key = console.readCharacter();
////                //key = cr.readVirtualKey();
////                System.out.println(key);
////            }
////
////        } catch(IOException e) {
////            e.printStackTrace();
////        }
////    }
//}
