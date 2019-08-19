import org.apache.commons.lang.StringUtils;
import vn.locdt.jats.module.generator.TemplateProducer;
import vn.locdt.jats.module.generator.exception.TemplateException;
import vn.locdt.jats.synergix.generator.TH6BeanGenerator;
import vn.locdt.jats.synergix.generator.TH6ServiceGenerator;
import vn.locdt.jats.synergix.generator.context.TH6BeanContext;
import vn.locdt.jats.synergix.generator.context.TH6ServiceContext;
import vn.locdt.jats.synergix.generator.context.model.FormModel;

import java.io.IOException;

public class TH6ServiceGeneratorTest {
    private static final String TH6_ROOT_PACKAGE = "synergix.th6";
    private static final String TH6_BEAN_PATH = "business.action.uibean";

    public static void main(String[] args) throws IOException, TemplateException {
        TemplateProducer producer = TemplateProducer.createProducer(TH6BeanGenerator.class, "template");

        FormModel formModel = new FormModel("TH6_FORM_CODE", "PJ", "SUB");

        String formContextName = "exampleTest";
        String parentBeanName = "SingleUIBean";

        formModel.setContextName(formContextName);
        formModel.setParentBeanName(parentBeanName);

        String projectPath = TH6_BEAN_PATH + "." + formModel.getModule().toLowerCase();
        String outputName = StringUtils.capitalize(formContextName) + "Bean";
        String packageName = TH6_ROOT_PACKAGE + "." + projectPath;

        TH6ServiceContext context = new TH6ServiceContext(formModel, "generated", outputName, packageName);
        TH6ServiceGenerator generator = new TH6ServiceGenerator(context);

        generator.generate(producer);

//        Data
    }
}
