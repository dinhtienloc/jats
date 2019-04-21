package vn.locdt.jats.synergix.addon.question;

import vn.locdt.jats.bundle.question.JQuestion;
import vn.locdt.jats.module.generator.TemplateProducer;
import vn.locdt.jats.module.shell.question.ChainingQuestionCLI;
import vn.locdt.jats.module.shell.question.QuestionCLI;
import vn.locdt.jats.module.shell.question.QuestionStatus;
import vn.locdt.jats.synergix.generator.TH6BeanGenerator;
import vn.locdt.jats.synergix.generator.context.TH6BeanContext;
import vn.locdt.jats.synergix.generator.context.model.FormModel;

import java.util.Arrays;
import java.util.List;

public class CreateFormQuestion extends ChainingQuestionCLI<FormModel, FormModel> {
    private static final String TH6_ROOT_PACKAGE = "synergix.th6";
    private static final String TH6_BEAN_PATH = "business.action.uibean";
    @Override
    protected void run() {
        TemplateProducer producer = TemplateProducer.createProducer("template");

        String formContextName = JQuestion.input(this.lineReader, "Your form context name:").getValue();
        String parentBeanName = JQuestion.select(this.lineReader,"Choose parent UIBean:", "parentBean", getUIBeanList()).getValue();
        String tableName = JQuestion.select(this.lineReader,"Choose table:", "table", Arrays.asList("t1", "t2", "t3")).getValue();

        this.getParameter().setContextName(formContextName);
        this.getParameter().setParentBeanName(parentBeanName);
        this.getParameter().setTableName(tableName);

        String projectPath = TH6_BEAN_PATH + "." + this.getParameter().getModule().toLowerCase();
        String outputName = formContextName + "Bean";
        String packageName = TH6_ROOT_PACKAGE + "." + projectPath;

        TH6BeanContext context = new TH6BeanContext(this.getParameter(), TH6_ROOT_PACKAGE, projectPath, outputName, packageName);
        TH6BeanGenerator th6BeanGenerator = new TH6BeanGenerator(context);
        th6BeanGenerator.setTemplateName("Bean.ftl");

//        th6BeanGenerator.generate(producer);

        if (th6BeanGenerator.generate(producer))
            status = QuestionStatus.CONTINUE;
        else
            status = QuestionStatus.STOP;
    }

    @Override
    public FormModel getOutputData() {
        return this.getParameter();
    }

    private List<String> getUIBeanList() {
        return Arrays.asList(
            "SingleViewGlobalMaintenanceUiBean",
            "SingleViewUIBean",
            "SummaryDetailGlobalMaintenanceUiBean",
            "SummaryDetailUIBean"
        );
    }
}
