package vn.locdt.jats.module.modeling.system;

import vn.locdt.jats.module.modeling.DatabaseMetadataWrapper;
import vn.locdt.jats.module.modeling.ResultSetExtractor;
import vn.locdt.jats.module.modeling.exception.CatalogNotSupportException;
import vn.locdt.jats.module.modeling.exception.SchemaNotSupportException;
import vn.locdt.jats.module.modeling.model.*;

import java.sql.ResultSet;

/**
 * Created by locdt on 1/31/2018.
 */
public abstract class SystemModeling {
    protected DatabaseMetadataWrapper wrapper;
    protected ResultSetExtractor extractor;
    protected String catalog;

    public SystemModeling() {}

    public SystemModeling(DatabaseMetadataWrapper wrapper) {
        this.wrapper = wrapper;
    }

    public DatabaseMetadataWrapper getWrapper() {
        return this.wrapper;
    }

    public void setWrapper(DatabaseMetadataWrapper wrapper) {
        this.wrapper = wrapper;
    }

    public ResultSetExtractor getExtractor() {
        return this.extractor;
    }

    public void setExtractor(ResultSetExtractor extractor) {
        this.extractor = extractor;
    }

    public abstract SystemModeling addExtractor(ResultSetExtractor extractor);

    public String getCatalog() {
        return this.catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public abstract Catalog model();

    public abstract Catalog model(String catalogName);

    public abstract Catalog modelCatalog(ResultSet catalog) throws CatalogNotSupportException;

    public abstract Schema modelSchema(ResultSet schema) throws SchemaNotSupportException;

    public abstract Table modelTable(Catalog catalog, ResultSet table);


    public abstract Column modelColumn(Table table, ResultSet column);

    public abstract void modelPrimaryKey(Table table, ResultSet pk);

    public abstract Relation modelForeignKey(Catalog catalog, ResultSet fk);
}
