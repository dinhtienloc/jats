package vn.locdt.jats.module.shell.question.init;

import vn.locdt.jats.bundle.question.JQuestion;
import vn.locdt.jats.module.shell.question.QuestionCLI;
import vn.locdt.jats.module.shell.question.QuestionStatus;
import vn.locdt.jats.module.shell.setting.SettingData;

/**
 * Created by locdt on 1/26/2018.
 */
public class RootPackageQuestion extends QuestionCLI {
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
        askForRootPackage();
        return QuestionStatus.CONTINUE;
    }

    private void askForRootPackage() {
        String rootPackage = JQuestion.input("Your root package:", "rootPackage").getValue();
        SettingData.getProjectSetting().setRootPackage(rootPackage);
    }
}
