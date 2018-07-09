package vn.locdt.jats.entity.generator.context;

public class DAOImplContext extends JavaClassContext {
	@Override
	public String getClassName() {
		return contextModel.getJavaName() + "DAOImpl";
	}

	@Override
	public String getExtendStatement() {
		return String.format(EXTEND_STATEMENT, "BaseDAO");
	}

	@Override
	public String getImplementStatement() {
		return String.format(IMPLEMENT_STATEMENT, "I" + contextModel.getJavaName() + "DAO");
	}
}
