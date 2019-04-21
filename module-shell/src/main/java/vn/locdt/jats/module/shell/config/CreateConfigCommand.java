package vn.locdt.jats.module.shell.config;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import vn.locdt.jats.module.shell.context.ContextKey;
import vn.locdt.jats.module.shell.context.ShellRuntimeContext;
import vn.locdt.jats.util.common.LogUtils;

@ShellComponent
public class CreateConfigCommand {

	@ShellMethod(key = { "config:add"}, value = "Create a global config")
	public String runCommand(
			@ShellOption(value = {"-n", "--name"}) String propertyName,
			@ShellOption(value = {"-p", "--prop"}) String propertyValue) {

		ContextKey key = ShellRuntimeContext.getContextKey(propertyName);
		if (key != null) {
			ShellRuntimeContext.addContext(key, propertyValue);
		}
		return LogUtils.createSuccessLog("Save config successfully!");
	}
}
