package vn.locdt.jats.common.addon.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import vn.locdt.jats.module.shell.command.QuestionCommand;
import vn.locdt.jats.module.shell.question.annotation.QuestionImports;
import vn.locdt.jats.module.shell.setting.SettingData;
import vn.locdt.jats.util.common.FileUtils;
import vn.locdt.jats.util.common.LogUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@ShellComponent
@QuestionImports({})
public class ClearConfigCommand extends QuestionCommand {

    @ShellMethod(key = {"config:clear"}, value = "Clear all saved setting in current project")
    public void runCommand() {
//        try {
//            SettingData.clearSetting();
//            Files.deleteIfExists(Paths.get(FileUtils.getConfigurationPath()));
//            LogUtils.printSuccessLog("All settings are removed!");
//        } catch (IOException e) {
//            e.printStackTrace();
//            LogUtils.printErrorLog("Could not remove settings!");
//        }
    }
}
