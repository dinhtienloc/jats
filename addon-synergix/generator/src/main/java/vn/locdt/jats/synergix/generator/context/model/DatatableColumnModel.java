package vn.locdt.jats.synergix.generator.context.model;

import java.util.Arrays;

public class DatatableColumnModel {
    private String name;
    private String labelKey;
    private String dataType;
    private String styleClass;
    private boolean notNull;

    public DatatableColumnModel(String name, String labelKey, String dataType, String styleClass, boolean notNull) {
        this.name = name;
        this.labelKey = labelKey;
        this.dataType = dataType;
        this.styleClass = styleClass;
        this.notNull = notNull;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabelKey() {
        return this.labelKey;
    }

    public void setLabelKey(String labelKey) {
        this.labelKey = labelKey;
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

    public String getStyleClass() {
        return this.styleClass;
    }

    public void setStyleClass(String styleClass) {
        String solveStyleClass = this.solveStyleClassFromDataType();
        if (solveStyleClass != null) {
            this.styleClass = solveStyleClass;
        } else {
            this.styleClass = styleClass;
        }
    }

    private String solveStyleClassFromDataType() {
        if ("REVISION_NO".equalsIgnoreCase(this.dataType) || "REV_NO".equalsIgnoreCase(this.dataType)) {
            return "revision";
        }

        return null;
    }

    public String getOutputComponent() {
        if (Arrays.asList("double", "currency-amt", "quantity", "unit-price", "integer", "long").contains(this.dataType)) {
            return "outputNumber";
        } else if (Arrays.asList("code", "description", "name", "remark", "document").contains(this.dataType)) {
            return "outputLabel";
        } else if (Arrays.asList("date", "time", "timestamp").contains(this.dataType)) {
            return "outputDate";
        }
        return null;
    }
}
