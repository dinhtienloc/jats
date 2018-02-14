package vn.locdt.jats.addon.entity.modeling.system.mysql;

import vn.locdt.jats.addon.entity.modeling.model.*;
import vn.locdt.jats.addon.entity.modeling.ResultSetExtractor;
import vn.locdt.jats.addon.entity.modeling.system.SystemModeling;
import vn.locdt.jats.addon.entity.modeling.exception.SchemaNotSupportException;
import vn.locdt.jats.addon.entity.modeling.util.SQL;
import vn.locdt.jats.addon.entity.modeling.util.DatabaseUtils;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by locdt on 1/31/2018.
 */
public class MySQLSystemModeling extends SystemModeling {
    public static final String SYSTEM = "MySQL";
    public static final List<String> sysCatalogs = Arrays.asList("information_schema", "performance_schema", "sys");

    public MySQLSystemModeling() {
        super();
        this.extractor = new MySQLResultSetExtractor();
    }

    @Override
    public SystemModeling addExtractor(ResultSetExtractor extractor) {
        this.setExtractor(new MySQLResultSetExtractor());
        return this;
    }

    @Override
    public List<Catalog> model() {
        List<Catalog> catalogs = new ArrayList<>();
        return SQL.wrap(() -> {
            ResultSet rs = wrapper.getMetaData().getCatalogs();
            while (rs.next()) {
                String catalog = rs.getString("TABLE_CAT");

                if (this.catalog != null) {
                    if (catalog.equals(this.catalog)) {
                        catalogs.add(modelCatalog(rs));
                        break;
                    }
                }
                else if (!sysCatalogs.contains(catalog)) {
                    catalogs.add(modelCatalog(rs));
                }
                else {
                    System.out.println("Could not model a system catalog.");
                }
            }
            return catalogs;
        }, new ArrayList<>());
    }

    @Override
    public Catalog modelCatalog(ResultSet rs) {
        Catalog catalog = extractor.catalog(rs);
        wrapper.setCatalog(catalog.getName());
        wrapper.getAllTables().forEach(row -> modelTable(catalog, row));
        catalog.getTables().forEach(
            table -> wrapper.getForeignKeys(table.getName()).forEach(
                row -> modelForeignKey(catalog, row)
            )
        );
        System.out.println(">>>>>>>>>>>> Modeling catalog: " + catalog);
        return catalog;
    }

    @Override
    public Schema modelSchema(ResultSet schemaRs) throws SchemaNotSupportException {
        throw new SchemaNotSupportException("Schema is not support in '" + SYSTEM + "' database");
    }

    @Override
    public Table modelTable(Catalog catalog, ResultSet rs) {
        Table table = extractor.table(rs);

        wrapper.getAllColumns(table.getName()).forEach(row -> modelColumn(table, row));
        wrapper.getPrimaryKeys(table.getName()).forEach(row -> modelPrimaryKey(table, row));


        catalog.addTable(table);
        table.setCatalog(catalog);
        System.out.println(">>>> Modeling Table: " + table);
        return table;
    }

    @Override
    public Column modelColumn(Table table, ResultSet rs) {
        Column col = extractor.column(rs);
        col.setTable(table);
        table.addColumn(col);
        System.out.println("> Modeling Column: " + col);
        return col;
    }

    @Override
    public void modelPrimaryKey(Table table, ResultSet rs) {
        String pkName = extractor.primaryKey(rs);
        table.getColumns().forEach(col -> {
            if (col.getName().equals(pkName))
                col.setPrimaryKey(true);
        });
    }

    @Override
    public Relation modelForeignKey(Catalog catalog, ResultSet rs) {
        Relation relation = extractor.relation(rs);

        Column pkColumn = DatabaseUtils.findColumnInTableByName(catalog,
                                                relation.getParentTableName(),
                                                relation.getParentColumnName());
        if (pkColumn == null) return null;

        Column fkColumn = DatabaseUtils.findColumnInTableByName(catalog,
                                                relation.getChildTableName(),
                                                relation.getChildColumnName());
        if (fkColumn == null) return null;

        relation.setParentColumn(pkColumn);
        relation.setChildColumn(fkColumn);

        fkColumn.addRelation(relation);
        pkColumn.addRelation(relation);

        return relation;
    }

}
