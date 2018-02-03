package vn.locdt.jats.addon.entity.modeling;

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
        return metaData;
    }

    public void setMetaData(DatabaseMetaData metaData) {
        this.metaData = metaData;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public ResultSetIterator<ResultSet> getTables(String namePattern) {
        return createResultSetIterator(() 
                -> this.getMetaData().getTables(this.getCatalog(), null, namePattern, new String[]{"TABLE"}));
    }

    public ResultSetIterator<ResultSet> getAllTables() {
        return getTables(null);
    }

    public ResultSetIterator<ResultSet> getViews(String namePattern) {
        return createResultSetIterator(()
                -> this.getMetaData().getTables(this.getCatalog(), null, namePattern, new String[]{"VIEW"}));
    }

    public ResultSetIterator<ResultSet> getColumns(String tableNamePattern, String columnNamePattern) {
        return createResultSetIterator(()
                -> this.getMetaData().getColumns(this.getCatalog(), null, tableNamePattern, columnNamePattern));

    }

    public ResultSetIterator<ResultSet> getAllColumns(String tableNamePattern) {
        return getColumns(tableNamePattern, null);
    }

    public ResultSetIterator<ResultSet> getPrimaryKeys(String tableNamePattern) {
        return createResultSetIterator(()
                -> this.getMetaData().getPrimaryKeys(this.getCatalog(), null, tableNamePattern));
    }

    public ResultSetIterator<ResultSet> getForeignKeys(String tableNamePattern) {
        return createResultSetIterator(()
                -> this.getMetaData().getImportedKeys(this.getCatalog(), null, tableNamePattern));
    }

    public ResultSetIterator<ResultSet> getIndices(String tableNamePattern, boolean unique, boolean approximate) {
        return createResultSetIterator(()
                -> this.getMetaData().getIndexInfo(this.getCatalog(), null, tableNamePattern, unique, approximate));
    }

    private ResultSetIterator<ResultSet> createResultSetIterator(Callable<ResultSet> callable) {
        try {
            return new ResultSetIterator(callable.call());
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
