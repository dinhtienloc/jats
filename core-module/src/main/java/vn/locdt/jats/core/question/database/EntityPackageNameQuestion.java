package vn.locdt.jats.core.question.database;

import vn.locdt.jats.core.question.JQuestion;
import vn.locdt.jats.core.QuestionCLI;
import vn.locdt.jats.core.QuestionStatus;
import vn.locdt.jats.core.setting.SettingData;
/**
 * Created by locdt on 1/26/2018.
 */
public class EntityPackageNameQuestion extends QuestionCLI {
    @Override
    protected QuestionStatus preQuestion() {
        return QuestionStatus.CONTINUE;
    }

    @Override
    protected QuestionStatus postQuestion() {
        return QuestionStatus.FINISHED;
    }

    @Override
    protected QuestionStatus run() {
        askForEntityPackageName();
        return QuestionStatus.CONTINUE;
    }

    private void askForEntityPackageName() {
        String entityFolder = JQuestion.input("Your entity package name:", "entityFolder").getValue();
        SettingData.getProjectSetting().setEntityFolder(entityFolder);
    }
}
