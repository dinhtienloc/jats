package vn.locdt.jats.entity.addon.question;

import vn.locdt.jats.bundle.question.JQuestion;
import vn.locdt.jats.module.shell.question.QuestionCLI;
import vn.locdt.jats.module.shell.question.QuestionStatus;
import vn.locdt.jats.module.shell.setting.SettingData;
import vn.locdt.jats.util.LogUtils;

/**
 * Created by locdt on 1/26/2018.
 */
public class EntityPackageNameQuestion extends QuestionCLI {

    @Override
    protected void postQuestion() {
        if (QuestionStatus.CONTINUE.equals(status))
            SettingData.save();
        super.postQuestion();
    }

    @Override
    public void preQuestion() {
        if (SettingData.getProjectSetting().getEntityFolder() != null)
            status = QuestionStatus.FINISHED;
        else
            status = QuestionStatus.CONTINUE;
    }

    @Override
    protected void run() {
        if (askForEntityPackageName())
            status = QuestionStatus.CONTINUE;
        else
            status = QuestionStatus.STOP;
    }

    private boolean askForEntityPackageName() {
        String entityFolder = JQuestion.input("Your entity package name:", "entityFolder").getValue();
        if (entityFolder.contains("-")) {
            LogUtils.printErrorLog("Package can not contain '-'");
            return false;
        }

        SettingData.getProjectSetting().setEntityFolder(entityFolder);
        return true;
    }
}
