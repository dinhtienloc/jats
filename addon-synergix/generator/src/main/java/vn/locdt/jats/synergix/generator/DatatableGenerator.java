package vn.locdt.jats.synergix.generator;

import vn.locdt.jats.module.generator.Generator;
import vn.locdt.jats.synergix.generator.context.DatatableContext;

public class DatatableGenerator extends Generator<DatatableContext> {
	public DatatableGenerator(DatatableContext context) {
		super(context);
		this.templateName = "Datatable.ftl";
	}
}
