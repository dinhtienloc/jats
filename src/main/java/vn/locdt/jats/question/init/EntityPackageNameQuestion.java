package vn.locdt.jats.question.init;

import vn.locdt.jats.question.QuestionCLI;
import vn.locdt.jats.setting.SettingData;
import vn.locdt.jquestion.JQuestion;

/**
 * Created by locdt on 1/26/2018.
 */
public class EntityPackageNameQuestion extends QuestionCLI {
    @Override
    protected RunStatus preQuestion() {
        return RunStatus.CONTINUE;
    }

    @Override
    protected RunStatus postQuestion() {
        return RunStatus.FINISHED;
    }

    @Override
    protected RunStatus run() {
        askForEntityPackageName();
        return RunStatus.CONTINUE;
    }

    private void askForEntityPackageName() {
        String entityFolder = JQuestion.input("Your entity package name:", "entityFolder").getValue();
        SettingData.getProjectSetting().setEntityFolder(entityFolder);
    }
}
