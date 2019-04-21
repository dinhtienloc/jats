package vn.locdt.jats.synergix.generator.context.model;

public class FormModel {
    private String formCode;
    private String module;
    private String transactionTypeCode;

    private String tableName;
    private String contextName;
    private String parentBeanName;

    public FormModel(String formCode, String module, String transactionTypeCode) {
        this.formCode = formCode;
        this.module = module;
        this.transactionTypeCode = transactionTypeCode;
    }

    public String getFormCode() {
        return formCode;
    }

    public void setFormCode(String formCode) {
        this.formCode = formCode;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getTransactionTypeCode() {
        return transactionTypeCode;
    }

    public void setTransactionTypeCode(String transactionTypeCode) {
        this.transactionTypeCode = transactionTypeCode;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getContextName() {
        return contextName;
    }

    public void setContextName(String contextName) {
        this.contextName = contextName;
    }

    public String getParentBeanName() {
        return parentBeanName;
    }

    public void setParentBeanName(String parentBeanName) {
        this.parentBeanName = parentBeanName;
    }
}
