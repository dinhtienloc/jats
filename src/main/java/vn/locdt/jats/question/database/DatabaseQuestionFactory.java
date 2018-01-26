package vn.locdt.jats.question.database;

import vn.locdt.jats.question.QuestionImports;
import vn.locdt.jats.question.QuestionFactory;

/**
 * Created by locdt on 1/22/2018.
 */
@QuestionImports({
        ConnectionQuestion.class,
        EntityPackageNameQuestion.class,
        PojoGeneratorQuestion.class
})
public class DatabaseQuestionFactory extends QuestionFactory {}
