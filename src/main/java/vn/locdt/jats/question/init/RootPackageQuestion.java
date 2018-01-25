package vn.locdt.jats.question.init;

import vn.locdt.jats.question.QuestionCLI;
import vn.locdt.jats.setting.SettingData;
import vn.locdt.jquestion.JQuestion;

/**
 * Created by locdt on 1/26/2018.
 */
public class RootPackageQuestion extends QuestionCLI {
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
        askForRootPackage();
        return RunStatus.CONTINUE;
    }

    private void askForRootPackage() {
        String rootPackage = JQuestion.input("Your root package:", "rootPackage").getValue();
        SettingData.getProjectSetting().setRootPackage(rootPackage);
    }
}
