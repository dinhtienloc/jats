package vn.locdt.jats.core.question.database;

import vn.locdt.jats.core.QuestionCLI;
import vn.locdt.jats.core.QuestionStatus;
import vn.locdt.jats.core.setting.SettingData;
import vn.locdt.jats.addon.question.JQuestion;
import vn.locdt.jats.core.internal.database.DatabaseReader;

import java.util.List;

/**
 * Created by locdt on 1/22/2018.
 */
public class PojoGeneratorQuestion extends QuestionCLI {
    public PojoGeneratorQuestion() {super();}

    @Override
    protected QuestionStatus preQuestion() {
        return QuestionStatus.CONTINUE;
    }

    @Override
    protected QuestionStatus postQuestion() {
        System.out.println("Generated successfully");
        return QuestionStatus.FINISHED;
    }

    @Override
    protected QuestionStatus run() {
        String chosenTable = askForTableName();
        return QuestionStatus.CONTINUE;
    }

    private String askForTableName(){
        String chosenTable = null;
        DatabaseReader dbReader = new DatabaseReader(SettingData.getDatabaseSetting().getConnection());
        List<String> tables = dbReader.getDatabaseTableList();

        if (tables != null) {
            if (tables.size() == 0)
                System.out.println("There is no table in database.");
            else
                chosenTable = JQuestion.select("Choose table?", "table", tables).getValue();
        }

        return chosenTable;
    }
}
