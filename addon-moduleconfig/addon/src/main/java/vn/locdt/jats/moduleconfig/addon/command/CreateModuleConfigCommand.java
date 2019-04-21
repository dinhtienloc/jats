package vn.locdt.jats.moduleconfig.addon.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class CreateModuleConfigCommand {
	@ShellMethod(key = { "moduleconfig:create"}, value = "Create module config")
	public String runCommand(
			@ShellOption(value = {"db"}) String dbType,
			@ShellOption(value = {"url"}) String dbUrl,
			@ShellOption(value = {"u, user"}) String dbUser,
			@ShellOption(value = {"p, pass"}) String dbPass) {
		return "Hello";

	}
}
