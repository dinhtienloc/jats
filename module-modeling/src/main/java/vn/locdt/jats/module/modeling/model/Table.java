package vn.locdt.jats.module.modeling.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by locdt on 1/29/2018.
 */
public class Table extends Model {
    private Catalog catalog;
    private String catalogName;
    private Schema schema;
    private List<Column> columns;

    public Table() {
        super();
        this.columns = new ArrayList<>();
    }

    public Table(String name) {
        super(name);
        this.columns = new ArrayList<>();
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public String getCatalogName() {
        return this.catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public List<Column> getPrimaryKeys() {
        return getColumns().stream()
                .filter(col -> col.isPrimaryKey())
                .collect(Collectors.toList());
    }

    public void addColumn(Column column) {
        if (column != null) this.columns.add(column);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Table)) return false;
        Table table = (Table) o;
        return Objects.equals(getCatalog(), table.getCatalog()) &&
                Objects.equals(getSchema(), table.getSchema()) &&
                Objects.equals(getName(), table.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCatalog(), getSchema(), getName());
    }

    @Override
    public String toString() {
        return "Table{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", catalog=" + catalog.getName() +
                ", schema=" + schema +
                ", columnsSize=" + columns.size() +
                '}';
    }
}
