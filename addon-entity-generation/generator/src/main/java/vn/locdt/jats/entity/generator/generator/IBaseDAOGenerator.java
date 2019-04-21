package vn.locdt.jats.entity.generator.generator;

import vn.locdt.jats.entity.generator.context.JavaClassContext;
import vn.locdt.jats.module.generator.Generator;

public class IBaseDAOGenerator extends Generator<JavaClassContext> {
	public IBaseDAOGenerator(JavaClassContext context) {
		super(context);
		this.templateName = "IBaseDAO.ftl";
	}

	@Override
	protected void prepareContext() {
		context.importClassQuietly("java.io.Serializable");
		context.importClassQuietly("java.util.List");
	}

}
