package vn.locdt.jats.question.factory;

import vn.locdt.jats.question.QuestionImports;
import vn.locdt.jats.question.database.ConnectionQuestion;
import vn.locdt.jats.question.database.EntityPackageNameQuestion;
import vn.locdt.jats.question.database.PojoGeneratorQuestion;

/**
 * Created by locdt on 1/22/2018.
 */
@QuestionImports({
        ConnectionQuestion.class,
        EntityPackageNameQuestion.class,
        PojoGeneratorQuestion.class
})
public class DatabaseQuestionFactory extends QuestionFactory {}
