package vn.locdt.jats.core.question.factory;

import vn.locdt.jats.core.question.database.ConnectionQuestion;
import vn.locdt.jats.core.QuestionImports;
import vn.locdt.jats.core.question.database.EntityPackageNameQuestion;
import vn.locdt.jats.core.question.database.PojoGeneratorQuestion;

/**
 * Created by locdt on 1/22/2018.
 */
@QuestionImports({
        ConnectionQuestion.class,
        EntityPackageNameQuestion.class,
        PojoGeneratorQuestion.class
})
public class DatabaseQuestionFactory extends QuestionFactory {}
