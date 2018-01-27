package vn.locdt.jats.shell.command;

import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;
import vn.locdt.jats.question.factory.InitQuestionFactory;
import vn.locdt.jats.setting.SettingData;
import vn.locdt.jats.util.Utils;

/**
 * Created by locdt on 1/26/2018.
 */
@Component
public class InitCommand implements CommandMarker {
    @CliCommand(value = { "init"})
    public String initJats(
            @CliOption(key = "rootPackage") String rootPackage
    ) {
        Utils.printDebugLog(rootPackage);
        if (rootPackage != null)
            SettingData.getProjectSetting().setRootPackage(rootPackage);
        else if (SettingData.getProjectSetting().getRootPackage() == null)
            new InitQuestionFactory().start();

        return null;
    }
}