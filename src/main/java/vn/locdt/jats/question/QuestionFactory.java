package vn.locdt.jats.question;

import vn.locdt.jats.setting.SettingData;
import vn.locdt.jats.util.Utils;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * Created by locdt on 1/22/2018.
 */
public abstract class QuestionFactory {

    protected abstract List<Class> create();
    public QuestionStatus start() {
        List<Class> questionCollection = create();
        for (Class clazz : questionCollection) {
            QuestionStatus status = createAndStartQuestionInstance(clazz);
//            Utils.printDebugLog("Class: " + clazz.getCanonicalName() + " | Status: " + status.name());
            if (status.equals(QuestionStatus.STOP))
                return QuestionStatus.STOP;
        }
        SettingData.save();
        return QuestionStatus.FINISHED;
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
}
