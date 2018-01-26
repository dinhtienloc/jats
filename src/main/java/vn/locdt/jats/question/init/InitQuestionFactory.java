package vn.locdt.jats.question.init;

import vn.locdt.jats.question.QuestionFactory;
import vn.locdt.jats.question.init.RootPackageQuestion;

import java.util.Arrays;
import java.util.List;

/**
 * Created by locdt on 1/27/2018.
 */
public class InitQuestionFactory extends QuestionFactory {
    @Override
    protected List<Class> create() {
        return Arrays.asList(RootPackageQuestion.class);
    }
}
