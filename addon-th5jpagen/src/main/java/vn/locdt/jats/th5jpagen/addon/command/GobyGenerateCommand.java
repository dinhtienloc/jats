package vn.locdt.jats.th5jpagen.addon.command;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Objects;

import goby.threading.ThreadStorageException;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.util.Assert;
import vn.locdt.jats.module.shell.command.QuestionCommand;
import vn.locdt.jats.module.shell.context.ContextKey;
import vn.locdt.jats.module.shell.context.ShellRuntimeContext;
import vn.locdt.jats.module.shell.exception.ContextNotFoundException;
import vn.locdt.jats.synergix.addon.db.DatabaseInfo;
import vn.locdt.jats.th5jpagen.addon.adapter.GobyGeneratorRunner;
import vn.locdt.jats.util.common.LogUtils;

@ShellComponent
public class GobyGenerateCommand extends QuestionCommand {

	@ShellMethod(key = {"goby"}, value = "Goby Generator - Generate TH5 Entity from database")
	public String runCommand() {

		LogUtils.printSuccessLog("Checking database connection");
		try {
			// just check whether user have connected to db yet.
			ShellRuntimeContext.getContext(ContextKey.DATABASE_INFO, DatabaseInfo.class);

			GobyGeneratorRunner gobyRunner = new GobyGeneratorRunner(this.getLineReader());
			gobyRunner.run();
		} catch (ContextNotFoundException e) {
			LogUtils.printErrorLog("Please run 'connect' to setup your connection first.");
		}

		return null;
	}
}
