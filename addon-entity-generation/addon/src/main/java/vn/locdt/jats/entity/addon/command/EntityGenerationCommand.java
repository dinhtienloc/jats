package vn.locdt.jats.entity.addon.command;

import org.fusesource.jansi.Ansi;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.stereotype.Component;
import vn.locdt.jats.module.shell.question.QuestionStatus;
import vn.locdt.jats.module.shell.command.QuestionCommand;
import vn.locdt.jats.module.shell.question.annotation.QuestionImports;
import vn.locdt.jats.module.shell.setting.SettingData;
import vn.locdt.jats.entity.addon.question.ConnectionQuestion;
import vn.locdt.jats.entity.addon.question.EntityPackageNameQuestion;
import vn.locdt.jats.entity.addon.question.PojoGeneratorQuestion;
import vn.locdt.jats.util.LogUtils;

import static org.fusesource.jansi.Ansi.ansi;

/**
 * Created by locdt on 1/22/2018.
 */

@Component
@QuestionImports({
        ConnectionQuestion.class,
        EntityPackageNameQuestion.class,
        PojoGeneratorQuestion.class
})
public class EntityGenerationCommand extends QuestionCommand implements CommandMarker {
    @CliCommand(value = { "entity:gen"})
    public String runCommand() {
        resolveOptionValues();

        if (SettingData.getProjectSetting().getRootPackage() == null)
            return LogUtils.createWarningLog("Please use 'init' command to setup jats with your project first!");

        LogUtils.printLog("Generating entity...");
        if (!SettingData.isHbmConfigurationCreated()) {
            QuestionStatus status = startQuestions();
            if (status.equals(QuestionStatus.STOP)) return null;
        }

        return LogUtils.createSuccessLog("Generate successfully");
    }
}
