package vn.locdt.jats.synergix.addon.param;

public class ColumnExpression {
    private int index;
    private String labelKey;
    private String synColumnStyleClass;

    public ColumnExpression(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getLabelKey() {
        return this.labelKey;
    }

    public void setLabelKey(String labelKey) {
        this.labelKey = labelKey;
    }

    public String getSynColumnStyleClass() {
        return this.synColumnStyleClass;
    }

    public void setSynColumnStyleClass(String synColumnStyleClass) {
        this.synColumnStyleClass = synColumnStyleClass;
    }
}
