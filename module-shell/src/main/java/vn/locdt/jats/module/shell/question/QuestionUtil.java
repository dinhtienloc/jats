package vn.locdt.jats.module.shell.question;

import java.lang.reflect.Constructor;

public class QuestionUtil {
    public static <Q> Q activeQuestion(Class<Q> questionClass) {
        try {
            Object o = questionClass.newInstance();
            Q question = questionClass.cast(o);
            return question;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
}
