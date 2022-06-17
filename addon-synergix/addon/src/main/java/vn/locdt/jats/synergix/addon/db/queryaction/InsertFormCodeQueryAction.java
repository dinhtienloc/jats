package vn.locdt.jats.synergix.addon.db.queryaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertFormCodeQueryAction extends QueryAction {

    public InsertFormCodeQueryAction(Connection con, Object... params) {
        super(con, params);
    }

    @Override
    public void createQuery() throws SQLException {
        PreparedStatement stmt = this.con.prepareStatement(
                "INSERT INTO FORM_MASTER ( FORM_CODE, FORM_NAME, URL, MODULE_CODE, CREATED_BY, VERSION_NO, IMPLEMENTED_STATUS, OBJECT_VERSION) " +
                        "VALUES ( ?, ?, '', ?, ?, 6, 'I', 0)");
        stmt.setString(1, (String) this.params[0]);
        stmt.setString(2, (String) this.params[1]);
        stmt.setString(3, (String) this.params[2]);
        stmt.setString(4, (String) this.params[3]);
        stmt.executeUpdate();
        this.con.commit();
    }
}
