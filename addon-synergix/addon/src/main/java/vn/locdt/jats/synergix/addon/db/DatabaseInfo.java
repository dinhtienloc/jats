package vn.locdt.jats.synergix.addon.db;

import vn.locdt.jats.util.common.LogUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class DatabaseInfo {

	public static final String DB2 = "DB2";
	public static final String POSTGRESQL = "POSTGRESQL";
	public static final String HSQL = "HSQL";
	public static final String XML = "XML";

	public static final String DB2_DRIVER = "com.ibm.db2.jcc.DB2Driver";
	public static final String POSTGRESQL_DRIVER = "org.postgresql.Driver";

	public static String currentType = null;
	
	public final String alias;
	public final String type;
	public final String url;
	public final String version;
	public final String user;
	public final String password;
	public final String schema;
	public final String category;
	public final boolean canConvertDatatype;
	public final String driver;
	private Connection conn;
	private DatabaseGroup group;
	
	public DatabaseInfo(String alias, String type, String version, String url, String user, String password, String schema, String category) {
		this.alias = alias;
		this.type = type;
		this.driver = DB2.equalsIgnoreCase(type) ? DB2_DRIVER : POSTGRESQL_DRIVER;
		this.url = url;
		this.version = version;
		this.user = user;
		this.password = password;
		this.schema = POSTGRESQL.equalsIgnoreCase(this.type) ? schema.toLowerCase() : schema.toUpperCase();
		
		this.category = category.trim().toUpperCase();
		
		// capability
		this.canConvertDatatype = this.type.equals(DB2) && this.version.equals("9.7");
	}

	public DatabaseGroup getGroup() {
		return this.group;
	}

	public void setGroup(DatabaseGroup group) {
		this.group = group;
	}

	public Connection getConnection() {
		try {
			if (this.conn != null && !this.conn.isClosed())
				return this.conn;

			Properties connectionProps = new Properties();
			connectionProps.put("user", this.user);
			connectionProps.put("password", this.password);
	    

	    	this.conn = DriverManager.getConnection(this.url, connectionProps);
		    if (this.conn  == null) throw new IllegalStateException("invalid database configuration");
		    return this.conn;
	    } catch (Exception e) {
	    	throw new RuntimeException(e);
	    }		
	}

	public void closeConnection() {
		try {
			if (this.conn != null && !this.conn.isClosed()) {
				this.conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return this.alias;
	}
	//	public static String getDefaultSchemaFileNameForDb(String db) {
//		DatabaseInfo dbInfo = getDatabaseInfo(db);
//		if ("main".equalsIgnoreCase(dbInfo.category)) return "modmain.xml";
//		else if ("ctrl".equalsIgnoreCase(dbInfo.category)) return "modctrl.xml";
//		else throw new IllegalStateException("Invalid database category: " + dbInfo.category);
//	}
}
