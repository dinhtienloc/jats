package vn.locdt.jats.question;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * Created by locdt on 1/22/2018.
 */
public abstract class QuestionFactory {

    protected abstract List<Class> create();
    public void start() {
        List<Class> questionCollection = create();
        questionCollection.forEach(QuestionFactory::createAndStartQuestionInstance);
        System.exit(1);
    }

    private static void createAndStartQuestionInstance(Class clazz)  {
        try {
            Constructor constructor = clazz.getConstructor();
            QuestionCLI question = (QuestionCLI) constructor.newInstance();
            question.start();
        } catch (Exception e) {
            return;
        }
    }
}
