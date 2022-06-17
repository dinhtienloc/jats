package vn.locdt.jats.module.modeling.system.postgres;

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

public class PostgreSQLSystemModeling extends SystemModeling {
    public static final String SYSTEM = "POSTGRESQL";
    public static final List<String> sysCatalogs = Arrays.asList("information_schema", "performance_schema", "sys");

    public PostgreSQLSystemModeling() {
        super();
        this.extractor = new PostgreSQLResultSetExtractor();
    }

    @Override
    public SystemModeling addExtractor(ResultSetExtractor extractor) {
        this.setExtractor(new PostgreSQLResultSetExtractor());
        return this;
    }

    @Override
    public Catalog model() {
        return SQL.wrap(() -> {
            Catalog result = null;
            ResultSet rs = this.wrapper.getMetaData().getCatalogs();
            while (rs.next()) {
                String catalogDbName = rs.getString("TABLE_CAT");
                if (sysCatalogs.contains(this.catalog)) {
                    System.out.println("Could not model a system catalog.");
                    return null;
                }

                if (catalogDbName.equals(this.catalog)) {
                    result = this.modelCatalog(rs);
                }
            }
            return result;
        }, null);
    }

    @Override
    public Catalog model(String catalogName) {
        this.catalog = catalogName;
        return this.model();
    }

    @Override
    public Catalog modelCatalog(ResultSet rs) {
        Catalog catalog = this.extractor.catalog(rs);
        this.wrapper.setCatalog(catalog.getName());
        this.wrapper.getAllTables().forEach(row -> this.modelTable(catalog, row));
        catalog.getTables().forEach(
                table -> this.wrapper.getForeignKeys(table.getName()).forEach(
                        row -> this.modelForeignKey(catalog, row)
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
        Table table = this.extractor.table(rs);

        this.wrapper.getAllColumns(table.getName()).forEach(row -> this.modelColumn(table, row));
        this.wrapper.getPrimaryKeys(table.getName()).forEach(row -> this.modelPrimaryKey(table, row));


        if (catalog != null) {
            catalog.addTable(table);
            table.setCatalog(catalog);
        }
//        System.out.println(">>>> Modeling Table: " + table);
        return table;
    }

    @Override
    public Column modelColumn(Table table, ResultSet rs) {
        Column col = this.extractor.column(rs);
        col.setTable(table);
        table.addColumn(col);
//        System.out.println("> Modeling Column: " + col);
        return col;
    }

    @Override
    public void modelPrimaryKey(Table table, ResultSet rs) {
        String pkName = this.extractor.primaryKey(rs);
        table.getColumns().forEach(col -> {
            if (col.getName().equals(pkName))
                col.setPrimaryKey(true);
        });
    }

    @Override
    public Relation modelForeignKey(Catalog catalog, ResultSet rs) {
        Relation relation = this.extractor.relation(rs);

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
