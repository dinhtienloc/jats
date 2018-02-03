package vn.locdt.jats.addon.entity.modeling.model;

/**
 * Created by locdt on 2/2/2018.
 */
public class ForeignKey extends Model {
    private String referencedTableName;
    private String referencedColumnName;
    private String referencingTableName;
    private String referencingColumnName;
    private Column referencedColumn;
    private Column referencingColumn;
    private UpdateRule updateRule;
    private DeleteRule deleteRule;

    public String getReferencedTableName() {
        return referencedTableName;
    }

    public void setReferencedTableName(String referencedTableName) {
        this.referencedTableName = referencedTableName;
    }

    public String getReferencedColumnName() {
        return referencedColumnName;
    }

    public void setReferencedColumnName(String referencedColumnName) {
        this.referencedColumnName = referencedColumnName;
    }

    public String getReferencingTableName() {
        return referencingTableName;
    }

    public void setReferencingTableName(String referencingTableName) {
        this.referencingTableName = referencingTableName;
    }

    public String getReferencingColumnName() {
        return referencingColumnName;
    }

    public void setReferencingColumnName(String referencingColumnName) {
        this.referencingColumnName = referencingColumnName;
    }

    public Column getReferencedColumn() {
        return referencedColumn;
    }

    public void setReferencedColumn(Column referencedColumn) {
        this.referencedColumn = referencedColumn;
    }

    public Column getReferencingColumn() {
        return referencingColumn;
    }

    public void setReferencingColumn(Column referencingColumn) {
        this.referencingColumn = referencingColumn;
    }

    public UpdateRule getUpdateRule() {
        return updateRule;
    }

    public void setUpdateRule(UpdateRule updateRule) {
        this.updateRule = updateRule;
    }

    public void setUpdateRule(short type) {
        this.updateRule = UpdateRule.enumOf(type);
    }

    public DeleteRule getDeleteRule() {
        return deleteRule;
    }

    public void setDeleteRule(DeleteRule deleteRule) {
        this.deleteRule = deleteRule;
    }

    public void setDeleteRule(short type) {
        this.deleteRule = DeleteRule.enumOf(type);
    }

    public enum UpdateRule {
        IMPORTED_NO_ACTION(0),
        IMPORTED_KEY_CASCADE(1),
        IMPOERTED_KEY_SET_NULL(2),
        IMPOERTED_KEY_SET_DEFAULT(3),
        IMPORTED_KEY_RESTRICT(4);
        private int type;
        UpdateRule(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public static UpdateRule enumOf(int type) {
            for (UpdateRule rule : UpdateRule.values()) {
                if (type == rule.getType()) return rule;
            }
            return null;
        }
    }

    public enum DeleteRule {
        IMPORTED_NO_ACTION(0),
        IMPORTED_KEY_CASCADE(1),
        IMPOERTED_KEY_SET_NULL(2),
        IMPOERTED_KEY_SET_DEFAULT(3),
        IMPORTED_KEY_RESTRICT(4);
        private int type;
        DeleteRule(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public static DeleteRule enumOf(int type) {
            for (DeleteRule rule : DeleteRule.values()) {
                if (type == rule.getType()) return rule;
            }
            return null;
        }
    }
}
