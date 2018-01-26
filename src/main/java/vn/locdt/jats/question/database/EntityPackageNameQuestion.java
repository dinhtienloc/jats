package vn.locdt.jats.question.database;

import vn.locdt.jats.question.QuestionCLI;
import vn.locdt.jats.question.QuestionStatus;
import vn.locdt.jats.setting.SettingData;
import vn.locdt.jquestion.JQuestion;

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
