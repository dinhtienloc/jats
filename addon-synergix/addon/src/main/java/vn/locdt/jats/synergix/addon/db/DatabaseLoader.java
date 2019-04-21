package vn.locdt.jats.synergix.addon.db;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import vn.locdt.jats.module.shell.context.ContextKey;
import vn.locdt.jats.module.shell.context.ShellRuntimeContext;
import vn.locdt.jats.util.common.FileUtils;
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
		File dbConfigFile = null;
		String dbConfigPath = ShellRuntimeContext.getContext(ContextKey.DB_CONFIG, String.class);

		try {
			if (dbConfigPath == null) {
				dbConfigFile = DatabaseLoader.createTempDbConfigFile();
				if (dbConfigFile != null) {
					String savedDbConfigPath = FileUtils.path(FileUtils.CONFIG_FOLDER_PATH, "dbconfig.xml");
					Files.copy(Paths.get(dbConfigFile.getAbsolutePath()), Paths.get(savedDbConfigPath));
					ShellRuntimeContext.addContext(ContextKey.DB_CONFIG, savedDbConfigPath);
				}
			}
			else {
				dbConfigFile = new File(dbConfigPath);
			}

			if (dbConfigFile != null) {
				Document doc = XMLUtils.parseFile(dbConfigFile);
				NodeList nl = doc.getElementsByTagName("group");

				for (int i=0; i<nl.getLength(); i++) {
					Element groupElm = (Element) nl.item(i);
					String groupName = groupElm.getAttribute("name");
					DatabaseGroup group = new DatabaseGroup(groupName);
					NodeList dbNodeList = groupElm.getChildNodes();

					for (int j = 0; j < dbNodeList.getLength();j++) {
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
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		DatabaseLoader.dbGroups = groups;
	}

	public static Set<DatabaseGroup> getDbGroups() {
		return DatabaseLoader.dbGroups;
	}

	public static DatabaseGroup getGroup(String name) {
		return DatabaseLoader.dbGroups.stream().filter(g -> name.equalsIgnoreCase(g.getName())).findFirst().orElse(null);
	}

	public static DatabaseInfo getInfo(String dbName) {
		if (StringUtils.isEmpty(dbName))
			return null;

		for (DatabaseGroup group : DatabaseLoader.dbGroups) {
			for (DatabaseInfo info : group.getDbInfos()) {
				if (info.alias.equalsIgnoreCase(dbName))
					return info;
			}
		}
		return null;

	}

	private static File createTempDbConfigFile() {
		try {
			ClassLoader classLoader = DatabaseLoader.class.getClassLoader();
			InputStream is = classLoader.getResourceAsStream("dbconfig-sample.xml");
			File tempFile = File.createTempFile("dbconfig", "xml");
			tempFile.deleteOnExit();
			FileOutputStream out = new FileOutputStream(tempFile);
			IOUtils.copy(is, out);
			return tempFile;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
//	public static String getDefaultSchemaFileNameForDb(String db) {
//		DatabaseInfo dbInfo = getDatabaseInfo(db);
//		if ("main".equalsIgnoreCase(dbInfo.category)) return "modmain.xml";
//		else if ("ctrl".equalsIgnoreCase(dbInfo.category)) return "modctrl.xml";
//		else throw new IllegalStateException("Invalid database category: " + dbInfo.category);
//	}
}
