package vn.locdt.jats.synergix.generator.context;

import org.apache.commons.lang.StringUtils;
import vn.locdt.jats.module.generator.context.JavaClassContext;
import vn.locdt.jats.synergix.generator.context.model.FormModel;
import vn.locdt.jats.synergix.generator.context.model.SynergixFormModel;

public class TH6ServiceContext<M extends SynergixFormModel> extends JavaClassContext<M> {
	public TH6ServiceContext(M contextModel, String outputPath, String outputName, String packageName) {
		super(contextModel, outputPath, outputName, packageName);
	}


	@Override
	public String getClassName() {
		return StringUtils.capitalize(this.getContextModel().getContextName()) + "Service";
	}

	@Override
	public String getExtendClassName() {
		return "AbstractService";
	}

	@Override
	public String getImplementClassName() {
		return null;
	}
}
