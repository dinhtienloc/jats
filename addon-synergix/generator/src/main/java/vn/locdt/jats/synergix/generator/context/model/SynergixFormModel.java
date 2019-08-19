package vn.locdt.jats.synergix.generator.context.model;

import org.apache.commons.lang.StringUtils;

public class SynergixFormModel {
	protected String code;
	protected String module;

	private String tableName;
	private String contextName;
	private String parentBeanName;

	public SynergixFormModel(String dashpaneCode, String module) {
		this.code = dashpaneCode;
		this.module = module;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getModule() {
		return this.module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getContextName() {
		return this.contextName;
	}

	public void setContextName(String contextName) {
		this.contextName = contextName;
	}

	public String getContextNameAsClassName() {
		return StringUtils.capitalize(this.contextName);
	}

	public String getContextNameAsVariable() {
		return StringUtils.uncapitalize(this.contextName);
	}

	public String getParentBeanName() {
		return this.parentBeanName;
	}

	public void setParentBeanName(String parentBeanName) {
		this.parentBeanName = parentBeanName;
	}
}
