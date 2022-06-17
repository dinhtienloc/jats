package vn.locdt.jats.synergix.addon.question;

import vn.locdt.jats.module.generator.TemplateProducer;
import vn.locdt.jats.module.generator.exception.TemplateException;
import vn.locdt.jats.synergix.generator.Th6WebPageGenerator;
import vn.locdt.jats.synergix.generator.context.TH6WebPageContext;
import vn.locdt.jats.synergix.generator.context.model.DashpaneModel;
import vn.locdt.jats.util.common.FileUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CreateDashpaneQuestion extends SynergixCreateFormQuesion<DashpaneModel> {

    public CreateDashpaneQuestion(DashpaneModel parameter) {
        super(parameter);
    }

    @Override
    protected void generateFormPage(TemplateProducer producer, String th6Path) throws IOException, TemplateException {
        String outputDirectory = FileUtils.path(th6Path, WEBAPP_PATH, "dashboards", "dashpane");
        String fileName = this.parameter.getCode().toLowerCase();

        TH6WebPageContext context = new TH6WebPageContext<>(this.getParameter(), outputDirectory, fileName);
        Th6WebPageGenerator generator = new Th6WebPageGenerator(context);
        generator.generate(producer);
    }

    @Override
    protected List<String> getParentBeans() {
        return Arrays.asList(
                "DashpaneBean",
                "FinancialPeriodDashpaneBean"
        );
    }
}
