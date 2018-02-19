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
    protected void postQuestion() {
        if (QuestionStatus.CONTINUE.equals(status))
            SettingData.save();
        super.postQuestion();
    }
    @Override
    protected void run() {
        askForRootPackage();
        status = QuestionStatus.CONTINUE;
    }

    private void askForRootPackage() {
        String rootPackage = JQuestion.input("Your root package:", "rootPackage").getValue();
        SettingData.getProjectSetting().setRootPackage(rootPackage);
    }
}
