package vn.locdt.jats.entity.generator.context;

import vn.locdt.jats.entity.generator.APIType;
import vn.locdt.jats.entity.generator.modeling.model.Table;

public class DAOContext extends JavaClassContext {
	private APIType apiType;

	public DAOContext(Table table, String rootPackage, String projectPath, String outputName, String packageName) {
		super(table, rootPackage, projectPath, outputName, packageName);
	}

	@Override
	public String getClassName() {
		return contextModel.getJavaName() + "DAO";
	}

	@Override
	public String getExtendStatement() {
		return null;
	}

	@Override
	public String getImplementStatement() {
		return null;
	}
}
