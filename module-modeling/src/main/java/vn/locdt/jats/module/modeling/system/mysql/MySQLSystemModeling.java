package vn.locdt.jats.module.modeling.system.mysql;

import vn.locdt.jats.module.modeling.ResultSetExtractor;
import vn.locdt.jats.module.modeling.exception.SchemaNotSupportException;
import vn.locdt.jats.module.modeling.model.Catalog;
import vn.locdt.jats.module.modeling.model.Column;
import vn.locdt.jats.module.modeling.model.Relation;
import vn.locdt.jats.module.modeling.model.Schema;
import vn.locdt.jats.module.modeling.model.Table;
import vn.locdt.jats.module.modeling.system.SystemModeling;
import vn.locdt.jats.module.modeling.util.DatabaseUtils;
import vn.locdt.jats.module.modeling.util.SQL;

import java.sql.ResultSet;
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
    public Catalog model() {
        return SQL.wrap(() -> {
            Catalog result = null;
            ResultSet rs = wrapper.getMetaData().getCatalogs();
            while (rs.next()) {
                String catalogDbName = rs.getString("TABLE_CAT");
                if (sysCatalogs.contains(catalog)) {
                    System.out.println("Could not model a system catalog.");
                    return null;
                }

                if (catalogDbName.equals(catalog)) {
                    result = modelCatalog(rs);
                }
            }
            return result;
        }, null);
    }

    @Override
    public Catalog model(String catalogName) {
        this.catalog = catalogName;
        return model();
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
//        System.out.println(">>>>>>>>>>>> Modeling catalog: " + catalog);
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
//        System.out.println(">>>> Modeling Table: " + table);
        return table;
    }

    @Override
    public Column modelColumn(Table table, ResultSet rs) {
        Column col = extractor.column(rs);
        col.setTable(table);
        table.addColumn(col);
//        System.out.println("> Modeling Column: " + col);
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
