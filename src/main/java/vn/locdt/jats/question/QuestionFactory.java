package vn.locdt.jats.question;

import vn.locdt.jats.exception.MismatchArgumentQuestionFactoryException;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * Created by locdt on 1/22/2018.
 */
public abstract class QuestionFactory {

    protected abstract List<Class> create() throws MismatchArgumentQuestionFactoryException;
    public void start() throws MismatchArgumentQuestionFactoryException {
        List<Class> questionCollection = create();
        questionCollection.forEach(QuestionFactory::createAndStartQuestionInstance);
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
