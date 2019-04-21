package vn.locdt.jats.synergix.generator.context.model;

public class DatatableColumnModel {
	private String name;
	private String dataType;
	private String styleClass;
	private boolean notNull;

	public DatatableColumnModel(String name, String dataType, String styleClass, boolean notNull) {
		this.name = name;
		this.dataType = dataType;
		this.styleClass = styleClass;
		this.notNull = notNull;
	}

	public String getName() {
		return this.name;
	}

	public String getDataType() {
		return this.dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public boolean isNotNull() {
		return this.notNull;
	}

	public void setNotNull(boolean notNull) {
		this.notNull = notNull;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStyleClass() {
		return this.styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}
}
