package vn.locdt.jats.entity.addon.command;

import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;
import vn.locdt.jats.module.shell.question.QuestionStatus;
import vn.locdt.jats.module.shell.command.QuestionCommand;
import vn.locdt.jats.module.shell.question.annotation.QuestionCliOption;
import vn.locdt.jats.module.shell.question.annotation.QuestionImports;
import vn.locdt.jats.module.shell.setting.DatabaseSetting;
import vn.locdt.jats.module.shell.setting.SettingData;
import vn.locdt.jats.entity.addon.question.ConnectionQuestion;
import vn.locdt.jats.entity.addon.question.EntityPackageNameQuestion;
import vn.locdt.jats.entity.addon.question.EntityGeneratorQuestion;
import vn.locdt.jats.util.LogUtils;

import static org.fusesource.jansi.Ansi.ansi;

/**
 * Created by locdt on 1/22/2018.
 */

@Component
@QuestionImports({
        ConnectionQuestion.class,
        EntityPackageNameQuestion.class,
        EntityGeneratorQuestion.class
})
public class EntityGenerationCommand extends QuestionCommand implements CommandMarker {
    @CliCommand(value = { "entity:gen"})
    public String runCommand(
            @CliOption(key = {"db"}) String dbType,
            @CliOption(key = {"url"}) String dbUrl,
            @CliOption(key = {"u, user"}) String dbUser,
            @CliOption(key = {"p, pass"}) String dbPass,
            @CliOption(key = {"f, folder"}) @QuestionCliOption(EntityPackageNameQuestion.class) String entityFolder) {
        resolveOptionValues();

        if (SettingData.getProjectSetting().getRootPackage() == null)
            return LogUtils.createWarningLog("Please use 'init' command to setup jats with your project first!");

        DatabaseSetting dbSetting = SettingData.getDatabaseSetting();
        dbSetting.setDbType(dbType);
        dbSetting.setDbUrl(dbUrl);
        dbSetting.setDbUser(dbUser);
        dbSetting.setDbPass(dbPass);

        SettingData.getProjectSetting().setEntityFolder(entityFolder);

        LogUtils.printLog("Generating entity...");

        QuestionStatus status = startQuestions();
        if (status.equals(QuestionStatus.STOP)) return null;
        SettingData.closeConnection();

        return LogUtils.createSuccessLog("Generate successfully");

    }
}
