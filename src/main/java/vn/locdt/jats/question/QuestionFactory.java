package vn.locdt.jats.question;

import vn.locdt.jats.setting.SettingData;
import vn.locdt.jats.util.Utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by locdt on 1/22/2018.
 */
public abstract class QuestionFactory {

    public QuestionStatus start() {
        QuestionStatus status = QuestionStatus.FINISHED;
        try {
            Class<?>[] questions = getImportedQuestions();
            Utils.printDebugLog(questions.length);
            for (Class clazz : questions) {
                status = createAndStartQuestionInstance(clazz);
                Utils.printDebugLog("Class: " + clazz.getCanonicalName() + " | Status: " + status.name());
                if (QuestionStatus.STOP.equals(status))
                    break;
            }
        } catch (Exception e) {
            Utils.printErrorLog("Error occurs when create questions in " + getClass().getCanonicalName());
            status = QuestionStatus.STOP;
        }

        if (QuestionStatus.FINISHED.equals(status))
            SettingData.save();

        return status;
    }

    private static QuestionStatus createAndStartQuestionInstance(Class clazz)  {
        QuestionStatus status;
        try {
            Constructor constructor = clazz.getConstructor();
            QuestionCLI q = (QuestionCLI) constructor.newInstance();
            status = q.start();
        } catch (Exception e) {
            status = QuestionStatus.STOP;
        }

        return status;
    }

    private Class<?>[] getImportedQuestions() throws Exception{
        for (Annotation a: getClass().getAnnotations()) {
//            Class<? extends Annotation> type = a.annotationType();
            return (Class<?>[]) a.getClass().getMethod("value").invoke(a);
        }
        return new Class<?>[]{};
    }
}
