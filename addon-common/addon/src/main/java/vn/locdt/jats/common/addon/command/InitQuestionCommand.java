package vn.locdt.jats.common.addon.command;

import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;
import vn.locdt.jats.module.shell.command.QuestionCommand;
import vn.locdt.jats.module.shell.question.annotation.QuestionCliOption;
import vn.locdt.jats.module.shell.question.annotation.QuestionImports;
import vn.locdt.jats.common.addon.question.RootPackageQuestion;
import vn.locdt.jats.module.shell.setting.ProjectSetting;
import vn.locdt.jats.module.shell.setting.SettingData;
import vn.locdt.jats.util.common.LogUtils;

/**
 * Created by locdt on 1/27/2018.
 */
@Component
@QuestionImports({RootPackageQuestion.class})
public class InitQuestionCommand extends QuestionCommand implements CommandMarker {

    @CliCommand(value = { "init"}, help = "Setup project with Jats. Need to run first with new project")
    public String runCommand(
        @CliOption(key = "rootPkg")
        @QuestionCliOption(RootPackageQuestion.class) String rootPackage
    ) {
        LogUtils.printDebugLog("Option: " + rootPackage);
        resolveOptionValues(rootPackage);

        LogUtils.printDebugLog("Root package: " + rootPackage);
        if (rootPackage != null) {
            ProjectSetting projectSetting = SettingData.getProjectSetting();
            projectSetting.setRootPackage(rootPackage);
            projectSetting.findRootPackagePath(rootPackage);
        }
        else startQuestions();
        return null;
    }
}
