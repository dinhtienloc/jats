package vn.locdt.jats.module.database;

import vn.locdt.jats.module.database.question.PojoGeneratorQuestion;
import vn.locdt.jats.question.QuestionFactory;
import vn.locdt.jats.module.database.question.ConnectionQuestion;
import java.util.Arrays;
import java.util.List;

/**
 * Created by locdt on 1/22/2018.
 */
public class DatabaseQuestionFactory extends QuestionFactory {

    @Override
    protected List<Class> create() {
        return Arrays.asList(
                ConnectionQuestion.class,
                PojoGeneratorQuestion.class);
    }
}
