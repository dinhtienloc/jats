package cli;

import constants.Constants;
import internal.database.DatabaseReader;
import internal.generate.POJOGenerator;
import vn.locdt.JQuestion;

import java.util.List;

/**
 * Created by locdt on 1/21/2018.
 */
public class PojoGeneratorQuestion extends QuestionCLI {
    public PojoGeneratorQuestion(JQuestion jQuestion) {
        super(jQuestion);
    }

    @Override
    protected void preQuestion() {
    }

    @Override
    protected void postQuestion() {
        System.out.println("Generated successfully");
    }

    @Override
    protected void run() {
        String chosenTable = askForTableName();
        POJOGenerator generator = new POJOGenerator(chosenTable);
        generator.generate();
    }

    private String askForTableName() {
        DatabaseReader dbReader = new DatabaseReader();
        List<String> tables = dbReader.getDatabaseTableList();
        String chosenTable = jQuestion.select("Choose table?", "table", tables).getValue();
        return chosenTable;
    }
}
