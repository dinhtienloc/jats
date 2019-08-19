package vn.locdt.jats.synergix.addon.question;

import org.springframework.util.StringUtils;
import vn.locdt.jats.bundle.question.JQuestion;
import vn.locdt.jats.module.generator.TemplateProducer;
import vn.locdt.jats.module.generator.exception.TemplateException;
import vn.locdt.jats.module.shell.context.ContextKey;
import vn.locdt.jats.module.shell.context.ShellRuntimeContext;
import vn.locdt.jats.module.shell.question.ParameterizedQuestionCLI;
import vn.locdt.jats.module.shell.question.QuestionStatus;
import vn.locdt.jats.module.shell.util.ParamAssert;
import vn.locdt.jats.synergix.generator.TH6BeanGenerator;
import vn.locdt.jats.synergix.generator.TH6ServiceGenerator;
import vn.locdt.jats.synergix.generator.context.TH6BeanContext;
import vn.locdt.jats.synergix.generator.context.TH6ServiceContext;
import vn.locdt.jats.synergix.generator.context.model.SynergixFormModel;
import vn.locdt.jats.util.common.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class SynergixCreateFormQuesion<M extends SynergixFormModel> extends ParameterizedQuestionCLI<M> {
	protected static final String JAVA_PATH = "TH6\\src\\main\\java";
	protected static final String WEBAPP_PATH = "TH6\\src\\main\\webapp";
	protected static final String TH6_ROOT_PACKAGE = "synergix.th6";
	protected static final String TH6_BEAN_PATH = "business.action.uibean";
	protected static final String TH6_SERVICE_PATH = "business.action.service";

	SynergixCreateFormQuesion(M contextModel) {
		super(contextModel);
	}

	@Override
	protected  boolean validateParameters() {
		return ParamAssert.notNull(this.getParameter().getCode(), "Form Code") &&
				ParamAssert.notNull(this.getParameter().getModule(), "Module Code");
	}

	@Override
	protected void run() {
		TemplateProducer producer = TemplateProducer.createProducer("template");

		String formContextName = JQuestion.input(this.lineReader, "Your form context name:").getValue();
		String parentBeanName = JQuestion.select(this.lineReader,"Choose parent Bean:", "parentBean", this.getParentBeans()).getValue();

		this.parameter.setContextName(formContextName);
		this.parameter.setParentBeanName(parentBeanName);
		try {
			String th6Path = ShellRuntimeContext.getContext(ContextKey.TH6_PATH, String.class);
			this.generateBean(producer, th6Path);
			this.generateService(producer, th6Path);
			this.generateFormPage(producer, th6Path);
			this.status = QuestionStatus.CONTINUE;
		}
		catch (IOException | TemplateException e) {
			e.printStackTrace();
			this.status = QuestionStatus.STOP;
		}
	}

	protected void generateBean(TemplateProducer producer, String th6Path) throws IOException, TemplateException {
		String projectPath = TH6_BEAN_PATH + "." + this.getParameter().getModule().toLowerCase();
		String outputName = StringUtils.capitalize(this.parameter.getContextName()) + "Bean";
		String packageName = TH6_ROOT_PACKAGE + "." + projectPath;

		String outputPath = FileUtils.path(th6Path, JAVA_PATH,
				(TH6_ROOT_PACKAGE + "." + projectPath).replace(".", File.separator));

		TH6BeanContext context = new TH6BeanContext<>(this.getParameter(), outputPath, outputName, packageName);
		TH6BeanGenerator generator = new TH6BeanGenerator(context);
		generator.generate(producer);
	}

	protected void generateService(TemplateProducer producer, String th6Path) throws IOException, TemplateException {
		String projectPath = TH6_SERVICE_PATH + "." + this.getParameter().getModule().toLowerCase();
		String outputName = StringUtils.capitalize(this.parameter.getContextName()) + "Service";
		String packageName = TH6_ROOT_PACKAGE + "." + projectPath;

		String outputPath = FileUtils.path(th6Path, JAVA_PATH,
				(TH6_ROOT_PACKAGE + "." + projectPath).replace(".", File.separator));

		TH6ServiceContext context = new TH6ServiceContext<>(this.getParameter(), outputPath, outputName, packageName);
		TH6ServiceGenerator generator = new TH6ServiceGenerator(context);
		generator.generate(producer);
	}

	protected abstract void generateFormPage(TemplateProducer producer, String th6Path) throws IOException, TemplateException;

	protected List<String> getParentBeans() {
		return null;
	}
}
