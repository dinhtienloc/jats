package vn.locdt.jats.common.addon.command;

import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.stereotype.Component;
import vn.locdt.jats.module.shell.command.QuestionCommand;
import vn.locdt.jats.module.shell.question.annotation.QuestionImports;
import vn.locdt.jats.module.shell.setting.SettingData;
import vn.locdt.jats.util.FileUtils;
import vn.locdt.jats.util.LogUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@QuestionImports({})
public class ClearConfigCommand extends QuestionCommand implements CommandMarker {

	@CliCommand(value = { "config:clear"}, help = "Clear all saved setting in current project")
	public void runCommand() {
		try {
			SettingData.clearSetting();
			Files.deleteIfExists(Paths.get(FileUtils.getConfigurationPath()));
			LogUtils.printSuccessLog("All settings are removed!");
		} catch (IOException e) {
			e.printStackTrace();
			LogUtils.printErrorLog("Could not remove settings!");
		}
	}
}
