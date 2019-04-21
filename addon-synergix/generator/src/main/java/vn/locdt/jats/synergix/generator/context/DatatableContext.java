package vn.locdt.jats.synergix.generator.context;

import vn.locdt.jats.module.generator.context.XHTMLContext;
import vn.locdt.jats.synergix.generator.context.model.DatatableModel;

public class DatatableContext extends XHTMLContext<DatatableModel> {

	public DatatableContext(DatatableModel contextModel, String outputDirectory, String outputName) {
		super(contextModel, outputDirectory, outputName);
	}

	public DatatableContext(DatatableModel contextModel) {
		super();
		this.contextModel = contextModel;
	}
}
