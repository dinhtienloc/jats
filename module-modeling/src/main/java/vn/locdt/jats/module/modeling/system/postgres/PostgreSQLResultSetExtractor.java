package vn.locdt.jats.module.modeling.system.postgres;

import vn.locdt.jats.module.modeling.ResultSetExtractor;
import vn.locdt.jats.module.modeling.model.*;
import vn.locdt.jats.module.modeling.strategy.IdentityStrategy;
import vn.locdt.jats.module.modeling.util.SQL;

import java.sql.ResultSet;

/**
 * Created by locdt on 2/3/2018.
 */
public class PostgreSQLResultSetExtractor extends ResultSetExtractor {

	@Override
	public Catalog catalog(ResultSet rs) {
		return SQL.wrap(() -> {
			String catalog = rs.getString("TABLE_CAT");
			return new Catalog(catalog);
		});
	}

	@Override
	public Schema schema(ResultSet rs) {
		return null;
	}

	public Table table(ResultSet rs) {
		return SQL.wrap(() -> {
			String tableName = rs.getString("TABLE_NAME");
			String catalogName = rs.getString("TABLE_CAT");
			Table table = new Table(tableName);
			table.setCatalogName(catalogName);
			return table;
		});
	}

	@Override
	public Column column(ResultSet rs) {
		return SQL.wrap(() -> {
			String columnName = rs.getString("COLUMN_NAME");
			int dataTypeCode = rs.getInt("DATA_TYPE");
			String dataType = rs.getString("TYPE_NAME");
			int columnSize = rs.getInt("COLUMN_SIZE");
			boolean nullable = rs.getBoolean("NULLABLE");
			boolean autoIncremented = "YES".equals(rs.getString("IS_AUTOINCREMENT"));

			Column col = new Column(columnName);
			col.setDataTypeCode(dataTypeCode);
			col.setDataType(dataType);
			col.setSize(columnSize);
			col.setNullable(nullable);
			col.setAutoIncrement(autoIncremented);

			if (autoIncremented)
				col.setGeneratedStrategy(new IdentityStrategy());
			return col;
		});
	}

	@Override
	public String primaryKey(ResultSet rs) {
		return SQL.wrap(() -> rs.getString("COLUMN_NAME"));
	}

	@Override
	public Relation relation(ResultSet rs) {
		return SQL.wrap(() -> {
			Relation relation = new Relation();

			String parentTableName = rs.getString("PKTABLE_NAME");
			relation.setParentTableName(parentTableName);
			String parentColumnName = rs.getString("PKCOLUMN_NAME");
			relation.setParentColumnName(parentColumnName);

			String childTableName = rs.getString("FKTABLE_NAME");
			relation.setChildTableName(childTableName);
			String childColumnName = rs.getString("FKCOLUMN_NAME");
			relation.setChildColumnName(childColumnName);

			short updateRule = rs.getShort("UPDATE_RULE");
			relation.setUpdateRule(updateRule);

			short deleteRule = rs.getShort("DELETE_RULE");
			relation.setDeleteRule(deleteRule);

			return relation;
		});
	}
}
