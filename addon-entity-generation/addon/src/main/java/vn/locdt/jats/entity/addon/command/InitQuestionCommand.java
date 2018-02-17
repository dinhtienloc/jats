package vn.locdt.jats.entity.addon.command;

import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;
import vn.locdt.jats.module.shell.command.QuestionCommand;
import vn.locdt.jats.module.shell.question.annotation.QuestionCliOption;
import vn.locdt.jats.module.shell.question.annotation.QuestionImports;
import vn.locdt.jats.module.shell.question.init.RootPackageQuestion;
import vn.locdt.jats.module.shell.setting.SettingData;
import vn.locdt.jats.util.LogUtils;

/**
 * Created by locdt on 1/27/2018.
 */
@QuestionImports({RootPackageQuestion.class})
@Component
public class InitQuestionCommand extends QuestionCommand implements CommandMarker {

    @CliCommand(value = { "init"})
    public String runCommand(
        @CliOption(key = "rootPackage")
        @QuestionCliOption(RootPackageQuestion.class) String rootPackage
    ) {
        resolveOptionValues(rootPackage);

        LogUtils.printDebugLog(rootPackage);
        if (rootPackage != null)
            SettingData.getProjectSetting().setRootPackage(rootPackage);

        startQuestions();
        return null;
    }
}