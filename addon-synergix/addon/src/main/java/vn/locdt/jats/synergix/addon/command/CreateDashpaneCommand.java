package vn.locdt.jats.synergix.addon.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import vn.locdt.jats.module.shell.command.QuestionCommand;
import vn.locdt.jats.module.shell.context.ContextKey;
import vn.locdt.jats.module.shell.context.ShellRuntimeContext;
import vn.locdt.jats.module.shell.exception.ContextNotFoundException;
import vn.locdt.jats.module.shell.question.QuestionStatus;
import vn.locdt.jats.synergix.addon.db.DatabaseInfo;
import vn.locdt.jats.synergix.addon.question.CreateDashpaneQuestion;
import vn.locdt.jats.synergix.addon.util.CommonUtils;
import vn.locdt.jats.synergix.generator.context.model.DashpaneModel;
import vn.locdt.jats.util.common.LogType;
import vn.locdt.jats.util.common.LogUtils;
import vn.locdt.jats.util.common.SVNUtil;
import vn.locdt.jats.util.exception.ErrorLogWaitException;

import java.sql.Connection;

import static org.springframework.shell.standard.ShellOption.NULL;

@ShellComponent
public class CreateDashpaneCommand extends QuestionCommand {
	@ShellMethod(key = "create:dashpane", value = "Create new dashpane")
	public String runCommand(
			@ShellOption(value = {"-m", "--module"})
					String moduleCode,

			@ShellOption(value = {"-r", "--role"})
					String role,

			@ShellOption(value = {"-c", "--code"})
					String formCode,

			@ShellOption(value = {"-d", "--desc"}, defaultValue = NULL)
					String description,

			@ShellOption(value = {"-t", "--title"})
					String title,

			@ShellOption(value = {"-u", "--user"})
					String createdBy,

			@ShellOption(value = "--codegen")
					boolean codeGen
	) {
//		this.resolveOptionValues(moduleCode, role, formCode, description, title, createdBy, Boolean.toString(codeGen));

		try {
			final SVNClientManager svnClientManager = SVNUtil.createSVNClientManager();

			// update supermodel to latest rev
			CommonUtils.updateSuperModel(svnClientManager);

			// append form query to 01_form_master.sql
			final String formQuery = "INSERT INTO SYS_DASHPANE (DASHPANE_CODE, DASHPANE_DESC, DASHPANE_TITLE, MODULE_CODE) VALUES " +
					"('" + formCode + "','" + description + "','','" + title + "','" + moduleCode.toUpperCase() + "')";
			CommonUtils.append01_form_master(formCode, createdBy, formQuery);

			// insert to db
			DatabaseInfo mainDbInfo = ShellRuntimeContext.getContext(ContextKey.DATABASE_INFO, DatabaseInfo.class);
			DatabaseInfo ctrlDbInfo = mainDbInfo.getGroup().getCtrlDb();

			LogUtils.printLogWait("Insert dashpane code " + LogUtils.bold(formCode) + " into Ctrl DB...", LogType.SUCCESS, () -> {
				Connection ctrlCon = ctrlDbInfo.getConnection();
				return CommonUtils.insertData(ctrlCon, formQuery);
			});

			// assign role to form code
			if (role != null) {
				CommonUtils.assignDashpanePermission(formCode, role, mainDbInfo);
			}
		} catch (ErrorLogWaitException e) {
			return "";
		} catch (ContextNotFoundException e) {
			LogUtils.printErrorLog(e.getMessage());
		}


		if (codeGen) {
			CreateDashpaneQuestion question = new CreateDashpaneQuestion(new DashpaneModel(formCode, moduleCode));
			question.setLineReader(this.getLineReader());
			QuestionStatus status = question.start();
			if (status.equals(QuestionStatus.CONTINUE)) {
				return LogUtils.createSuccessLog("Done");
			}
		}

		return "";
	}
}
