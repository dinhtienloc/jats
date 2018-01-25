package vn.locdt.jats.module.database.question;

import vn.locdt.jats.setting.SettingData;
import vn.locdt.jats.internal.database.DatabaseReader;
import vn.locdt.jats.internal.generate.POJOGenerator;
import vn.locdt.jats.question.QuestionCLI;
import vn.locdt.jquestion.JQuestion;

import java.util.List;

/**
 * Created by locdt on 1/22/2018.
 */
public class PojoGeneratorQuestion extends QuestionCLI {
    public PojoGeneratorQuestion() {super();}

    @Override
    protected RunStatus preQuestion() {
        return RunStatus.CONTINUE;
    }

    @Override
    protected RunStatus postQuestion() {
        System.out.println("Generated successfully");
        return RunStatus.FINISHED;
    }

    @Override
    protected RunStatus run() {
        String chosenTable = askForTableName();
        POJOGenerator generator = new POJOGenerator();
        generator.generate();
        return RunStatus.CONTINUE;
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
