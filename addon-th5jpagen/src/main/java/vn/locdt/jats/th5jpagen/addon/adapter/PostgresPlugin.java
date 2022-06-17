package vn.locdt.jats.th5jpagen.addon.adapter;

import goby.codegen.objectmodel.Entity;
import goby.codegen.objectmodel.FK;
import goby.codegen.objectmodel.JoinColumn;
import goby.codegen.objectmodel.Property;
import goby.codegen.plugin.DbMtPlugin;
import goby.collection.CollectionUtils;
import goby.collection.Predicate;
import goby.jdbc.JdbcUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class PostgresPlugin extends DbMtPlugin {
    @Override
    public void doPlugin(Entity entity) throws Exception {
        Connection conn = this.getConnection();

        try {
            ResultSet rs = conn.getMetaData().getTables(null, null, entity.getTableName().toUpperCase(), null);
            boolean tableExists = rs.next();

            if (!tableExists) {
                rs = conn.getMetaData().getTables(null, null, entity.getTableName().toLowerCase(), null);
                tableExists = rs.next();
            }

            if (tableExists) {
                entity.setTableCat(rs.getString("TABLE_CAT"));
                entity.setTableScheme(rs.getString("TABLE_SCHEM"));
                entity.setTableName(rs.getString("TABLE_NAME"));
                entity.setTableLcName(entity.getTableName().toLowerCase());
                entity.setTableUcName(entity.getTableName().toUpperCase());
                entity.setTableType(rs.getString("TABLE_TYPE"));
                entity.setName(this.getEntityNameBuilder().buildName(entity));
                entity.setLcName(entity.getName().toLowerCase());
                entity.setUcName(entity.getName().toUpperCase());
                entity.setTableAlias(this.getTableAliasBuilder().buildAlias(entity));
            }

            rs.close();
            if (!tableExists) {
                throw new Exception(String.format("Table %s does not exist.", entity.getTableName()));
            }

            List<String> primaryKeys = JdbcUtil.loadKeys(conn, entity.getTableName());
            rs = conn.getMetaData().getColumns(null, null, entity.getTableName(), null);

            while (rs.next()) {
                Property property = new Property();
                property.setColumnName(rs.getString("COLUMN_NAME"));
                property.setDbTypeName(rs.getString("TYPE_NAME").toUpperCase());
                property.setDbType(rs.getInt("DATA_TYPE"));
                property.setIsNullable(rs.getBoolean("NULLABLE"));
                property.setSize(rs.getInt("COLUMN_SIZE"));
                property.setOrdinalPosition(rs.getInt("ORDINAL_POSITION"));
                property.setColumnLcName(property.getColumnName().toLowerCase());
                property.setColumnUcName(property.getColumnName().toUpperCase());
                property.setIsKey(primaryKeys.contains(property.getColumnName()));
                property.setName(this.getPropertyNameBuilder().buildName(property));
                property.setLcName(property.getName().toLowerCase());
                property.setLabel(this.getPropertyLabelBuilder().buildLabel(property));
                property.setEntity(entity);
                entity.getPropertyList().add(property);
                if (property.getDbTypeName().contains(" IDENTITY")) {
                    property.setIsIdentity(true);
                    entity.setIsAutoIncrement(true);
                }
            }

            rs.close();
            Iterator i$ = entity.getPropertyList().iterator();

            while (i$.hasNext()) {
                Property prop = (Property) i$.next();
                if (prop.getIsKey()) {
                    entity.getKeyList().add(prop);
                } else {
                    entity.getNonKeyList().add(prop);
                }
            }

            (entity.getPropertyList().get(entity.getPropertyList().size() - 1)).setIsLast(true);
            (entity.getNonKeyList().get(entity.getNonKeyList().size() - 1)).setIsLastNonKey(true);
            if (entity.getKeyList().size() > 0) {
                (entity.getKeyList().get(entity.getKeyList().size() - 1)).setIsLastKey(true);
            }

            JoinColumn jc;
            int len;
            FK fk;
            try {
                final ResultSet fkrs = conn.getMetaData().getImportedKeys(null, null, entity.getTableName());

                while (fkrs.next()) {
                    fk = CollectionUtils.find(entity.getImpFkList(), new Predicate() {
                        public boolean evaluateOn(Object obj) {
                            FK fk = (FK) obj;

                            try {
                                return fkrs.getString("FK_NAME").equals(fk.getFk_name());
                            } catch (SQLException var4) {
                                return false;
                            }
                        }
                    });
                    if (fk == null) {
                        fk = new FK();
                        fk.setFk_name(fkrs.getString("FK_NAME"));
                        fk.getImportedEntity().setTableName(fkrs.getString("PKTABLE_NAME"));
                        fk.getImportedEntity().setName(this.getEntityNameBuilder().buildName(fk.getImportedEntity()));
                        fk.getImportedEntity().setLcName(fk.getImportedEntity().getName().toLowerCase());
                        fk.getImportedEntity().setUcName(fk.getImportedEntity().getName().toUpperCase());
                        entity.getImpFkList().add(fk);
                    }

                    jc = new JoinColumn();
                    jc.setName(fkrs.getString("FKCOLUMN_NAME"));
                    jc.setReferencedColumnName(fkrs.getString("PKCOLUMN_NAME"));
                    jc.setKey_seq(fkrs.getShort("KEY_SEQ"));
                    jc.setDelete_rule(fkrs.getShort("DELETE_RULE"));
                    jc.setUpdate_rule(fkrs.getShort("UPDATE_RULE"));
                    fk.getJoinColumnList().add(jc);
                }

                fkrs.close();
                i$ = entity.getImpFkList().iterator();

                while (i$.hasNext()) {
                    fk = (FK) i$.next();
                    len = fk.getJoinColumnList().size();
                    (fk.getJoinColumnList().get(len - 1)).setIsLast(true);
                }
            } catch (SQLException var15) {
                ;
            }

            try {
                final ResultSet fkrs = conn.getMetaData().getExportedKeys(null, null, entity.getTableName());

                while (fkrs.next()) {
                    fk = CollectionUtils.find(entity.getExpFkList(), new Predicate() {
                        public boolean evaluateOn(Object obj) {
                            FK fk = (FK) obj;

                            try {
                                return fkrs.getString("FK_NAME").equals(fk.getFk_name());
                            } catch (SQLException var4) {
                                return false;
                            }
                        }
                    });
                    if (fk == null) {
                        fk = new FK();
                        fk.setFk_name(fkrs.getString("FK_NAME"));
                        fk.getExportedEntity().setTableName(fkrs.getString("FKTABLE_NAME"));
                        fk.getExportedEntity().setName(this.getEntityNameBuilder().buildName(fk.getExportedEntity()));
                        fk.getExportedEntity().setLcName(fk.getExportedEntity().getName().toLowerCase());
                        fk.getExportedEntity().setUcName(fk.getExportedEntity().getName().toUpperCase());
                        entity.getExpFkList().add(fk);
                    }

                    jc = new JoinColumn();
                    jc.setName(fkrs.getString("PKCOLUMN_NAME"));
                    jc.setReferencedColumnName(fkrs.getString("FKCOLUMN_NAME"));
                    jc.setKey_seq(fkrs.getShort("KEY_SEQ"));
                    jc.setDelete_rule(fkrs.getShort("DELETE_RULE"));
                    jc.setUpdate_rule(fkrs.getShort("UPDATE_RULE"));
                    fk.getJoinColumnList().add(jc);
                }

                fkrs.close();
                i$ = entity.getExpFkList().iterator();

                while (i$.hasNext()) {
                    fk = (FK) i$.next();
                    len = fk.getJoinColumnList().size();
                    (fk.getJoinColumnList().get(len - 1)).setIsLast(true);
                }
            } catch (SQLException var16) {
                ;
            }
        } catch (Exception var17) {
            throw var17;
        } finally {
            JdbcUtil.closeConnection(conn);
        }

    }
}
