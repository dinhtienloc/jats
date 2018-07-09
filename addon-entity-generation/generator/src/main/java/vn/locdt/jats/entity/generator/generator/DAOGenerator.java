package vn.locdt.jats.entity.generator.generator;

import vn.locdt.jats.entity.generator.context.DAOContext;
import vn.locdt.jats.module.generator.Generator;

public class DAOGenerator extends Generator<DAOContext> {

	@Override
	protected void prepareContext() {
		context.importClassQuietly("java.io.Serializable");
		context.importClassQuietly("java.util.List");
	}
}
