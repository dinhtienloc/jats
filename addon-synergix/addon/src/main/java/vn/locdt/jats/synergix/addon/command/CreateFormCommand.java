package vn.locdt.jats.synergix.addon.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import vn.locdt.jats.module.shell.command.QuestionCommand;
import vn.locdt.jats.module.shell.context.ContextKey;
import vn.locdt.jats.module.shell.context.ShellRuntimeContext;
import vn.locdt.jats.module.shell.question.QuestionStatus;
import vn.locdt.jats.module.shell.question.annotation.QuestionImports;
import vn.locdt.jats.synergix.addon.db.DatabaseInfo;
import vn.locdt.jats.synergix.addon.db.queryaction.QueryAction;
import vn.locdt.jats.synergix.addon.question.CreateFormQuestion;
import vn.locdt.jats.synergix.addon.util.CommonUtils;
import vn.locdt.jats.synergix.generator.context.model.FormModel;
import vn.locdt.jats.util.common.*;
import vn.locdt.jats.util.exception.ErrorLogWaitException;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.fusesource.jansi.Ansi.ansi;
import static org.springframework.shell.standard.ShellOption.NULL;

@ShellComponent
@QuestionImports(CreateFormQuestion.class)
public class CreateFormCommand extends QuestionCommand {

	@ShellMethod(key = "create:form", value = "Create new form code")
	public String runCommand(
			@ShellOption(value = {"-m", "--module"}, defaultValue = NULL)
					String module,

			@ShellOption(value = {"-t", "--transaction"}, defaultValue = NULL)
					String transactionTypeCode,

			@ShellOption(value = {"-r", "--role"})
					String role,

			@ShellOption(value = {"-c", "--code"})
					String formCode,

			@ShellOption(value = {"-d", "--desc"}, defaultValue = NULL)
					String description,

			@ShellOption(value = {"-u", "--user"})
					String createdBy,

			@ShellOption(value = "--codegen")
					boolean codeGen
	) {
		this.resolveOptionValues(module, transactionTypeCode, formCode, description, Boolean.toString(codeGen));

		try {
			final SVNClientManager svnClientManager = SVNUtil.createSVNClientManager();

			// update supermodel to latest rev
			CommonUtils.updateSuperModel(svnClientManager);

			// append form query to 01_form_master.sql
			final String formQuery = "INSERT INTO FORM_MASTER (FORM_CODE,FORM_NAME,URL,MODULE_CODE,TRANSACTION_TYPE_CODE,CREATED_BY,VERSION_NO,IMPLEMENTED_STATUS,OBJECT_VERSION) " +
					"VALUES ('"+formCode+"','"+description+"','','"+(module != null ? module.toUpperCase() : null)+"','"+(transactionTypeCode != null ? transactionTypeCode.toUpperCase() : null)+"','"+createdBy+"', 6, 'I', 0)";
			CommonUtils.append01_form_master(formCode, createdBy, formQuery);

			// insert to db
			DatabaseInfo mainDbInfo = ShellRuntimeContext.getContext(ContextKey.DATABASE_INFO, DatabaseInfo.class);
			DatabaseInfo ctrlDbInfo = mainDbInfo.getGroup().getCtrlDb();

			LogUtils.printLogWait("Insert form code " + LogUtils.bold(formCode) + " into Ctrl DB...", LogType.SUCCESS, () -> {
				Connection ctrlCon = ctrlDbInfo.getConnection();
				return CommonUtils.insertData(ctrlCon, formQuery);
			});

			// assign role to form code
			if (role != null) {
				CommonUtils.assignRoleCodePermission(formCode, role, mainDbInfo);
			}
		}
		catch (ErrorLogWaitException e) {
			return "";
		}

		if (codeGen) {
			CreateFormQuestion question = new CreateFormQuestion(new FormModel(formCode, module, transactionTypeCode));
			question.setLineReader(this.getLineReader());
			QuestionStatus status = question.start();
			if (status.equals(QuestionStatus.CONTINUE)) {
				return LogUtils.createSuccessLog("Done");
			}
		}

		return LogUtils.createLog("You must add this form code to " + LogUtils.bold("menu.xml") + " yourself. Good luck!");
	}
}
