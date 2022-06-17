package vn.locdt.jats.module.modeling.model;

/**
 * Created by locdt on 2/2/2018.
 */
public class Relation extends Model {
    private String parentTableName;
    private String parentColumnName;
    private String childTableName;
    private String childColumnName;
    private Column parentColumn;
    private Column childColumn;
    private UpdateRule updateRule;
    private DeleteRule deleteRule;

    public String getParentTableName() {
        return parentTableName;
    }

    public void setParentTableName(String parentTableName) {
        this.parentTableName = parentTableName;
    }

    public String getParentColumnName() {
        return parentColumnName;
    }

    public void setParentColumnName(String parentColumnName) {
        this.parentColumnName = parentColumnName;
    }

    public String getChildTableName() {
        return childTableName;
    }

    public void setChildTableName(String childTableName) {
        this.childTableName = childTableName;
    }

    public String getChildColumnName() {
        return childColumnName;
    }

    public void setChildColumnName(String childColumnName) {
        this.childColumnName = childColumnName;
    }

    public Column getParentColumn() {
        return parentColumn;
    }

    public void setParentColumn(Column parentColumn) {
        this.parentColumn = parentColumn;
    }

    public Column getChildColumn() {
        return childColumn;
    }

    public void setChildColumn(Column childColumn) {
        this.childColumn = childColumn;
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

    @Override
    public String toString() {
        return "ForeignKey{" +
                "parentTableName='" + parentTableName + '\'' +
                ", parentColumnName='" + parentColumnName + '\'' +
                ", childTableName='" + childTableName + '\'' +
                ", childColumnName='" + childColumnName + '\'' +
                ", updateRule=" + updateRule +
                ", deleteRule=" + deleteRule +
                '}';
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
