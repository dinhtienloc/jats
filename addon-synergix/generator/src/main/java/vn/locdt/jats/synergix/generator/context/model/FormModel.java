package vn.locdt.jats.synergix.generator.context.model;

public class FormModel extends SynergixFormModel {
    private String transactionTypeCode;

    public FormModel(String formCode, String module, String transactionTypeCode) {
        super(formCode, module);
        this.transactionTypeCode = transactionTypeCode;
    }

    public String getTransactionTypeCode() {
        return this.transactionTypeCode;
    }

    public void setTransactionTypeCode(String transactionTypeCode) {
        this.transactionTypeCode = transactionTypeCode;
    }

    @Override
    public String toString() {
        return "FormModel{" +
                "transactionTypeCode='" + transactionTypeCode + '\'' +
                ", code='" + code + '\'' +
                ", module='" + module + '\'' +
                '}';
    }
}
