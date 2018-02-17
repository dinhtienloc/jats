package vn.locdt.jats.entity.addon.question;

import vn.locdt.jats.bundle.question.JQuestion;
import vn.locdt.jats.module.shell.question.QuestionCLI;
import vn.locdt.jats.module.shell.question.QuestionStatus;
import vn.locdt.jats.module.shell.setting.SettingData;

/**
 * Created by locdt on 1/26/2018.
 */
public class EntityPackageNameQuestion extends QuestionCLI {
    @Override
    protected void run() {
        askForEntityPackageName();
        status = QuestionStatus.CONTINUE;
    }

    private void askForEntityPackageName() {
        String entityFolder = JQuestion.input("Your entity package name:", "entityFolder").getValue();
        SettingData.getProjectSetting().setEntityFolder(entityFolder);
    }
}
