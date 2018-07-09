package vn.locdt.jats.util.common.intellj.command;

import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.stereotype.Component;

import vn.locdt.jats.util.common.intellj.execute.IntellJProjectEntryGenerator;

@Component
public class IntellJProjectEntryCommand implements CommandMarker {

	@CliCommand(value = { "intellj:entry-gem"}, help = "Generate an exe file of project which can open into IntellJ")
	public void runCommand() {
		IntellJProjectEntryGenerator.generateEntry();
	}
}