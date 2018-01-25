package vn.locdt.jats.shell.command;

import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;
import vn.locdt.jats.question.DatabaseQuestionFactory;
import vn.locdt.jats.question.init.EntityPackageNameQuestion;
import vn.locdt.jats.question.init.RootPackageQuestion;
import vn.locdt.jats.setting.SettingData;

/**
 * Created by locdt on 1/26/2018.
 */
@Component
public class InitCommand implements CommandMarker {
    @CliCommand(value = { "init"})
    public String initJats(
            @CliOption(key = "rootPackage") String rootPackage,
            @CliOption(key = "entityPackageName") String entityPackageName
    ) {
        if (rootPackage != null)
            SettingData.getProjectSetting().setRootPackage(rootPackage);
        else if (SettingData.getProjectSetting().getRootPackage() == null)
            new RootPackageQuestion().start();

        if (entityPackageName != null)
            SettingData.getProjectSetting().setEntityFolder(entityPackageName);
        else if (SettingData.getProjectSetting().getEntityFolder() == null)
            new EntityPackageNameQuestion().start();

        return "Entity folder output: " + SettingData.getEntityDirectoryPath();
    }
}
