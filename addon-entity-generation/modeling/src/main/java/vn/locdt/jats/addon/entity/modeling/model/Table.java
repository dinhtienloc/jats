package vn.locdt.jats.addon.entity.modeling.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by locdt on 1/29/2018.
 */
public class Table extends Model {
    private Catalog catalog;
    private Schema schema;
    private List<Column> columns;
    private List<ForeignKey> foreignKeys;

    public Table() {
        super();
        this.columns = new ArrayList<>();
        this.foreignKeys = new ArrayList<>();
    }

    public Table(String name) {
        super(name);
        this.columns = new ArrayList<>();
        this.foreignKeys = new ArrayList<>();
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

    public List<Column> getPrimaryKeys() {
        return getColumns().stream()
                .filter(col -> col.isPrimaryKey())
                .collect(Collectors.toList());
    }

    public List<ForeignKey> getForeignKeys() {
        return foreignKeys;
    }

    public void setForeignKeys(List<ForeignKey> foreignKeys) {
        this.foreignKeys = foreignKeys;
    }

    public void addColumn(Column column) {
        if (column != null) this.columns.add(column);
    }

    public void addForeignKey(ForeignKey fk) {
        if (fk != null) this.foreignKeys.add(fk);
    }

    @Override
    public String toString() {
        return "Table{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", catalog=" + catalog.getName() +
                ", schema=" + schema +
                ", columnsSize=" + columns.size() +
                ", foreignKeys=" + foreignKeys.size() +
                '}';
    }
}
