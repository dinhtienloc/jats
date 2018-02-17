package vn.locdt.jats.entity.addon.question;

import vn.locdt.jats.bundle.question.JQuestion;
import vn.locdt.jats.entity.addon.internal.DatabaseReader;
import vn.locdt.jats.module.shell.question.QuestionCLI;
import vn.locdt.jats.module.shell.question.QuestionStatus;
import vn.locdt.jats.module.shell.setting.SettingData;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by locdt on 1/22/2018.
 */
public class PojoGeneratorQuestion extends QuestionCLI {

    @Override
    protected void run() {
        String chosenTable = askForTableName();
        status = QuestionStatus.CONTINUE;
    }

    private String askForTableName(){
        try {
            String chosenTable = null;
            Connection connection = SettingData.getDatabaseSetting().getConnection();
            DatabaseReader dbReader = new DatabaseReader(connection);
            List<String> tables = dbReader.getDatabaseTableList();

            if (tables != null) {
                if (tables.size() == 0)
                    System.out.println("There is no table in database.");
                else
                    chosenTable = JQuestion.select("Choose table?", "table", tables).getValue();
            }

            connection.close();
            return chosenTable;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
