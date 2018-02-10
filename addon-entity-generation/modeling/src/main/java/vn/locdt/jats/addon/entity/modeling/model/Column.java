package vn.locdt.jats.addon.entity.modeling.model;

import vn.locdt.jats.addon.entity.modeling.system.mysql.MySQLDataTypeMapping;
import vn.locdt.jats.addon.entity.modeling.util.StringUtils;

/**
 * Created by locdt on 1/29/2018.
 */
public class Column extends Model {
    protected Table table;
    protected int dataTypeCode;
    protected String dataType;
    protected int size;
    protected boolean primaryKey;
    protected boolean autoIncrement;
    protected boolean generated;
    protected boolean nullable;


    public Column() {
        super();
    }

    public Column(String name) {
        super(name);
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public int getDataTypeCode() {
        return dataTypeCode;
    }

    public void setDataTypeCode(int dataTypeCode) {
        this.dataTypeCode = dataTypeCode;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public boolean isGenerated() {
        return generated;
    }

    public void setGenerated(boolean generated) {
        this.generated = generated;
    }

    public String getJavaType() { return MySQLDataTypeMapping.getJavaType(dataTypeCode);}

    @Override
    public String toString() {
        return "Column{" +
                "table=" + table.getName() +
                ", name='" + name + '\'' +
                ", dataTypeCode=" + dataTypeCode +
                ", description='" + description + '\'' +
                ", dataType='" + dataType + '\'' +
                ", size=" + size +
                ", primaryKey=" + primaryKey +
                ", autoIncrement=" + autoIncrement +
                ", generated=" + generated +
                ", nullable=" + nullable +
                '}';
    }
}
