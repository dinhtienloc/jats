package vn.locdt.jats.synergix.addon.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import vn.locdt.jats.module.shell.command.QuestionCommand;
import vn.locdt.jats.module.shell.context.ContextKey;
import vn.locdt.jats.module.shell.context.ShellRuntimeContext;
import vn.locdt.jats.module.shell.question.QuestionStatus;
import vn.locdt.jats.module.shell.question.annotation.QuestionImports;
import vn.locdt.jats.module.shell.question.annotation.QuestionShellOption;
import vn.locdt.jats.synergix.addon.db.DatabaseInfo;
import vn.locdt.jats.synergix.addon.db.queryaction.CreateFormCodeQueryAction;
import vn.locdt.jats.synergix.addon.db.queryaction.QueryAction;
import vn.locdt.jats.synergix.addon.question.CreateFormQuestion;
import vn.locdt.jats.synergix.generator.context.model.FormModel;
import vn.locdt.jats.util.common.LogUtils;

import static org.springframework.shell.standard.ShellOption.NULL;

@ShellComponent
@QuestionImports(CreateFormQuestion.class)
public class CreateFormCommand extends QuestionCommand {

	@ShellMethod(key = "create:form", value = "Create new form code")
    public String runCommand(
        @ShellOption(value = {"-c", "--code"})
        String formCode,

        @ShellOption(value = {"-m", "--module"}, defaultValue = NULL)
        String module,

        @ShellOption(value = {"-t", "--transaction"}, defaultValue = NULL)
        String transactionTypeCode,

        @ShellOption(value = "--codegen")
        @QuestionShellOption(value = CreateFormQuestion.class, activedValue = "false")
        boolean codeGen
    ) {
        this.resolveOptionValues(formCode, module, transactionTypeCode, Boolean.toString(codeGen));

        QueryAction qa = new CreateFormCodeQueryAction();
        boolean result = qa.action();

        if (result) {
            Parameters parameter = new Parameters()
                    .put(CreateFormQuestion.class, new FormModel(formCode, module, transactionTypeCode));;

            QuestionStatus status = this.startQuestions(parameter);
            if (status.equals(QuestionStatus.STOP)) return null;
            ShellRuntimeContext.getContext(ContextKey.DATABASE_INFO, DatabaseInfo.class).closeConnection();
        }

        return LogUtils.createSuccessLog("Generate successfully");
    }
}
