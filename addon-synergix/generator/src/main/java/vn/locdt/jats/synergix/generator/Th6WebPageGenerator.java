package vn.locdt.jats.synergix.generator;

import vn.locdt.jats.module.generator.Generator;
import vn.locdt.jats.synergix.generator.context.TH6WebPageContext;
import vn.locdt.jats.synergix.generator.context.model.DashpaneModel;
import vn.locdt.jats.synergix.generator.context.model.FormModel;

public class Th6WebPageGenerator extends Generator<TH6WebPageContext> {
	public Th6WebPageGenerator(TH6WebPageContext context) {
		super(context);

		if (context.getContextModel() instanceof FormModel) {
			this.templateName = "FormPage.ftl";
		}
		else if (context.getContextModel() instanceof DashpaneModel) {
			this.templateName = "Dashpane.ftl";
		}
	}
}
