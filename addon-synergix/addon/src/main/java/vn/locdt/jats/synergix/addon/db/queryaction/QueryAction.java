package vn.locdt.jats.synergix.addon.db.queryaction;

import vn.locdt.jats.module.modeling.util.SQL;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class QueryAction {
    protected Object[] params;
    protected Connection con;

    public QueryAction(Connection con, Object... params) {
        this.con = con;
        this.params = params;
    }

    public static QueryAction create(Class<? extends QueryAction> clazz, Connection con, Object... params) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<? extends QueryAction> cons = clazz.getConstructor(Connection.class, Object[].class);
        return cons.newInstance(con, params);
    }

    public static QueryAction create(Connection con, String query) {
        return new QueryAction(con) {
            @Override
            protected void createQuery() throws SQLException {
                PreparedStatement stmt = this.con.prepareStatement(query);
                stmt.executeUpdate();
            }
        };
    }

    protected void preAction() {}
    protected abstract void createQuery() throws SQLException;
    protected void postAction() {}

    public void execute() throws SQLException {
        this.preAction();
        this.createQuery();
        this.postAction();
        this.con.close();
    }
}
