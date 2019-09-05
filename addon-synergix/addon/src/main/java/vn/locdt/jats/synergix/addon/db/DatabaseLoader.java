package vn.locdt.jats.synergix.addon.db;

import org.apache.commons.io.IOUtils;
import org.springframework.util.CollectionUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import vn.locdt.jats.module.shell.context.ContextKey;
import vn.locdt.jats.module.shell.context.ShellRuntimeContext;
import vn.locdt.jats.util.common.FileUtils;
import vn.locdt.jats.util.common.LogUtils;
import vn.locdt.jats.util.common.StringUtils;
import vn.locdt.jats.util.common.XMLUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;


public class DatabaseLoader {
	private static Set<DatabaseGroup> dbGroups;

	static {
		Set<DatabaseGroup> groups = new HashSet<>();
		String dbConfigPath = FileUtils.path(FileUtils.getJarFileLocation(), "dbconfig.xml");

		File dbConfigFile = new File(dbConfigPath);
		if (!dbConfigFile.exists()) {
			LogUtils.printErrorLog("dbConfig.xml file not found: " + dbConfigPath);
		}
		else {

			ShellRuntimeContext.addContext(ContextKey.DB_CONFIG, dbConfigPath);
			LogUtils.printLog("Load database configurations from %s", dbConfigPath);

			Document doc = XMLUtils.parseFile(dbConfigFile);
			NodeList nl = doc.getElementsByTagName("group");

			for (int i = 0; i < nl.getLength(); i++) {
				Element groupElm = (Element) nl.item(i);
				String groupName = groupElm.getAttribute("name");
				DatabaseGroup group = new DatabaseGroup(groupName);
				NodeList dbNodeList = groupElm.getChildNodes();

				for (int j = 0; j < dbNodeList.getLength(); j++) {
					Node n = dbNodeList.item(j);
					if (n.getNodeType() == Node.ELEMENT_NODE) {
						Element database = (Element) n;
						String alias = database.getAttribute("alias");
						String type = database.getAttribute("type").trim().toUpperCase();
						String version = database.getAttribute("version").trim().toUpperCase();
						String url = database.getAttribute("url");
						String user = database.getAttribute("user");
						String password = database.getAttribute("password");
						String schema = database.getAttribute("schema").trim().toUpperCase();
						String category = database.getAttribute("category").trim().toUpperCase();

						group.addDatabaseInfo(new DatabaseInfo(alias, type, version, url, user, password, schema, category));
					}
				}
				groups.add(group);
			}

			DatabaseLoader.dbGroups = groups;
		}
	}

	public static Set<DatabaseGroup> getDbGroups() {
		return DatabaseLoader.dbGroups;
	}

	public static DatabaseGroup getGroup(String name) {
		return DatabaseLoader.dbGroups.stream().filter(g -> name.equalsIgnoreCase(g.getName())).findFirst().orElse(null);
	}

	public static DatabaseInfo getInfo(String dbName) {
		if (StringUtils.isEmpty(dbName) || CollectionUtils.isEmpty(DatabaseLoader.dbGroups))
			return null;

		for (DatabaseGroup group : DatabaseLoader.dbGroups) {
			for (DatabaseInfo info : group.getDbInfos()) {
				if (info.alias.equalsIgnoreCase(dbName))
					return info;
			}
		}
		return null;

	}

//	private static File createTempDbConfigFile() {
//		try {
//			ClassLoader classLoader = DatabaseLoader.class.getClassLoader();
//			InputStream is = classLoader.getResourceAsStream("dbconfig-sample.xml");
//			File tempFile = File.createTempFile("dbconfig", "xml");
//			tempFile.deleteOnExit();
//			FileOutputStream out = new FileOutputStream(tempFile);
//			IOUtils.copy(is, out);
//			return tempFile;
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
}
