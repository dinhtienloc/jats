package vn.locdt.jats.module.modeling;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;

/**
 * Created by locdt on 1/29/2018.
 */
public class DatabaseMetadataWrapper {
    private DatabaseMetaData metaData;
    private String catalog;

    public DatabaseMetadataWrapper(DatabaseMetaData metaData) {
        this.metaData = metaData;
    }

    public DatabaseMetaData getMetaData() {
        return this.metaData;
    }

    public void setMetaData(DatabaseMetaData metaData) {
        this.metaData = metaData;
    }

    public String getCatalog() {
        return this.catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public ResultSetIterator<ResultSet> getTables(String namePattern) {
        return this.createResultSetIterator(()
                -> this.getMetaData().getTables(this.getCatalog(), null, namePattern, new String[]{"TABLE"}));
    }

    public ResultSetIterator<ResultSet> getAllTables() {
        return this.getTables(null);
    }

    public ResultSetIterator<ResultSet> getViews(String namePattern) {
        return this.createResultSetIterator(()
                -> this.getMetaData().getTables(this.getCatalog(), null, namePattern, new String[]{"VIEW"}));
    }

    public ResultSetIterator<ResultSet> getColumns(String tableNamePattern, String columnNamePattern) {
        return this.createResultSetIterator(()
                -> this.getMetaData().getColumns(this.getCatalog(), null, tableNamePattern, columnNamePattern));

    }

    public ResultSetIterator<ResultSet> getAllColumns(String tableNamePattern) {
        return this.getColumns(tableNamePattern, null);
    }

    public ResultSetIterator<ResultSet> getPrimaryKeys(String tableNamePattern) {
        return this.createResultSetIterator(()
                -> this.getMetaData().getPrimaryKeys(this.getCatalog(), null, tableNamePattern));
    }

    public ResultSetIterator<ResultSet> getForeignKeys(String tableNamePattern) {
        return this.createResultSetIterator(()
                -> this.getMetaData().getImportedKeys(this.getCatalog(), null, tableNamePattern));
    }

    public ResultSetIterator<ResultSet> getIndices(String tableNamePattern, boolean unique, boolean approximate) {
        return this.createResultSetIterator(()
                -> this.getMetaData().getIndexInfo(this.getCatalog(), null, tableNamePattern, unique, approximate));
    }

    private ResultSetIterator<ResultSet> createResultSetIterator(Callable<ResultSet> callable) {
        try {
            return new ResultSetIterator<>(callable.call());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
