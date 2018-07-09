package vn.locdt.jats.util.intellij.command;

import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.stereotype.Component;
import vn.locdt.jats.util.intellij.execute.IntelliJProjectEntryGenerator;

@Component
public class IntelliJProjectEntryCommand implements CommandMarker {

	@CliCommand(value = { "intellij:entry-gen"}, help = "Generate an exe file of project which can open into IntellJ")
	public void runCommand() {
		IntelliJProjectEntryGenerator.generateEntry();
	}
}