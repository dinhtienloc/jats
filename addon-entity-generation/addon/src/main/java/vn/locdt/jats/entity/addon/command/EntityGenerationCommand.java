package vn.locdt.jats.entity.addon.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import vn.locdt.jats.module.shell.question.QuestionStatus;
import vn.locdt.jats.module.shell.command.QuestionCommand;
import vn.locdt.jats.module.shell.question.annotation.QuestionShellOption;
import vn.locdt.jats.module.shell.question.annotation.QuestionImports;
import vn.locdt.jats.module.shell.setting.DatabaseSetting;
import vn.locdt.jats.module.shell.setting.SettingData;
import vn.locdt.jats.entity.addon.question.ConnectionQuestion;
import vn.locdt.jats.entity.addon.question.EntityPackageNameQuestion;
import vn.locdt.jats.entity.addon.question.EntityGeneratorQuestion;
import vn.locdt.jats.util.common.LogUtils;

/**
 * Created by locdt on 1/22/2018.
 */

@ShellComponent
@QuestionImports({
        ConnectionQuestion.class,
        EntityPackageNameQuestion.class,
        EntityGeneratorQuestion.class
})
public class EntityGenerationCommand extends QuestionCommand {
    @ShellMethod(key = { "entity:gen"}, value = "Generate entity class")
    public String runCommand(
            @ShellOption(value = {"db"}) String dbType,
            @ShellOption(value = {"url"}) String dbUrl,
            @ShellOption(value = {"u, user"}) String dbUser,
            @ShellOption(value = {"p, pass"}) String dbPass,
            @ShellOption(value = {"f, folder"}) @QuestionShellOption(EntityPackageNameQuestion.class) String entityFolder) {
        if (SettingData.getProjectSetting().getRootPackage() == null)
            return LogUtils.createWarningLog("Please use 'init' command to setup jats with your project first!");

        resolveOptionValues(dbType, dbUrl, dbUser, dbPass, entityFolder);
        int count = countNotNullValues(dbType, dbUrl, dbUser, dbPass, entityFolder);

        if (count == -1) {
            return LogUtils.createWarningLog("Required arguments can not be null");
        }
        else if (count == 1) {
            DatabaseSetting dbSetting = SettingData.getDatabaseSetting();
            dbSetting.setDbType(dbType);
            dbSetting.setDbUrl(dbUrl);
            dbSetting.setDbUser(dbUser);
            dbSetting.setDbPass(dbPass);

            SettingData.getProjectSetting().setEntityFolder(entityFolder);
        }

        LogUtils.printLog("Generating entity...");

        QuestionStatus status = startQuestions();
        if (status.equals(QuestionStatus.STOP)) return null;
        SettingData.closeConnection();

        return LogUtils.createSuccessLog("Generate successfully");

    }
}
