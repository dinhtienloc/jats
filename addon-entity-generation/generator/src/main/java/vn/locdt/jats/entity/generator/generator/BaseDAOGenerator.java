package vn.locdt.jats.entity.generator.generator;

import vn.locdt.jats.entity.generator.context.JavaClassContext;
import vn.locdt.jats.module.generator.Generator;

public class BaseDAOGenerator extends Generator<JavaClassContext> {
	public BaseDAOGenerator() {
		this.templateName = "BaseDAO.ftl";
	}

	@Override
	protected void prepareContext() {
		context.importClassQuietly("java.io.IOException");
		context.importClassQuietly("java.io.InputStream");
		context.importClassQuietly("java.io.Serializable");
		context.importClassQuietly("java.lang.reflect.ParameterizedType");
		context.importClassQuietly("java.lang.reflect.Type");
		context.importClassQuietly("java.util.List");
		context.importClassQuietly("java.util.Properties");
		context.importClassQuietly("javax.persistence.EntityManager");
		context.importClassQuietly("javax.persistence.PersistenceContext");

		context.importClassQuietly(context.getRootPackage() + ".config.EntityManagerProvider");
		context.importClassQuietly(context.getRootPackage() + ".dao.IBaseDao");
		super.prepareContext();
	}
}
