package vn.locdt.jats.common.addon.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import vn.locdt.jats.module.shell.command.QuestionCommand;
import vn.locdt.jats.module.shell.question.annotation.QuestionShellOption;
import vn.locdt.jats.module.shell.question.annotation.QuestionImports;
import vn.locdt.jats.common.addon.question.RootPackageQuestion;
import vn.locdt.jats.module.shell.setting.ProjectSetting;
import vn.locdt.jats.module.shell.setting.SettingData;
import vn.locdt.jats.util.common.LogUtils;

import static org.springframework.shell.standard.ShellOption.NULL;

/**
 * Created by locdt on 1/27/2018.
 */
@ShellComponent
@QuestionImports({RootPackageQuestion.class})
public class InitQuestionCommand extends QuestionCommand {

    @ShellMethod(key = { "init"}, value = "Setup project with Jats. Need to run first with new project")
    public String runCommand(
        @ShellOption(value = "rootPkg", defaultValue = NULL)
        @QuestionShellOption(RootPackageQuestion.class) String rootPackage
    ) {
        LogUtils.printDebugLog("Option: " + rootPackage);
//        resolveOptionValues(rootPackage);

        LogUtils.printDebugLog("Root package: " + rootPackage);
        if (rootPackage != null) {
            ProjectSetting projectSetting = SettingData.getProjectSetting();
            projectSetting.setRootPackage(rootPackage);
            projectSetting.findRootPackagePath(rootPackage);
        }
        else startQuestions(null);
        return null;
    }
}
