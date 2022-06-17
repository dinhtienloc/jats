package vn.locdt.jats.synergix.addon.param;

import java.util.ArrayList;
import java.util.List;

public class OrderedColumnExpression {
    private List<ColumnExpression> columnExpressions;

    public OrderedColumnExpression() {
        this.columnExpressions = new ArrayList<>();
    }

    public List<ColumnExpression> getColumnExpressions() {
        return this.columnExpressions;
    }

    public void setColumnExpressions(List<ColumnExpression> columnExpressions) {
        this.columnExpressions = columnExpressions;
    }
}
