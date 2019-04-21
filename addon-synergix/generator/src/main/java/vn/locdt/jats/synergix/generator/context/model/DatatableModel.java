package vn.locdt.jats.synergix.generator.context.model;

import java.util.ArrayList;
import java.util.List;

public class DatatableModel {
	private String value;
	private String var;
	private String styleClass;
	private List<DatatableColumnModel> columns;

	public DatatableModel(String value, String var, String styleClass) {
		this.value = value;
		this.var = var;
		this.styleClass = styleClass;
		this.columns = new ArrayList<>();
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getVar() {
		return this.var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public String getStyleClass() {
		return this.styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public List<DatatableColumnModel> getColumns() {
		return this.columns;
	}

	public void setColumns(List<DatatableColumnModel> columns) {
		this.columns = columns;
	}
}
