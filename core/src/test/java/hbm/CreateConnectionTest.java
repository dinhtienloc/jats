//package hbm;
//
//import com.sun.rowset.WebRowSetImpl;
//import junit.framework.TestCase;
//import org.junit.Test;
//
//import java.io.IOException;
//import java.sql.*;
//
///**
// * Created by locdt on 1/28/2018.
// */
//public class CreateConnectionTest extends TestCase {
//    private Connection conn;
//
//    @Override
//    protected void setUp() {
//        try {
//            this.conn = DriverManager.getConnection(TestConstants.DB_URL,
//                                                    TestConstants.DB_USER,
//                                                    TestConstants.DB_PASS);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    @Test
//    public void testConnection() {
//        try {
//            DatabaseMetaData meta = conn.getMetaData();
//            ResultSet rs = meta.getTables(null, null, null, new String[]{"TABLE"});
//            WebRowSetImpl my_xml_data = new WebRowSetImpl();
//            my_xml_data.populate(rs);
//            my_xml_data.writeXml(System.out);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
