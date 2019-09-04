package vn.locdt.jats.th5jpagen.addon.adapter;

import goby.codegen.config.PluginXmlParser;
import goby.codegen.config.TemplateXmlParser;
import goby.codegen.objectmodel.Entity;
import goby.codegen.objectmodel.PluginXml;
import goby.codegen.objectmodel.Task;
import goby.codegen.objectmodel.TemplateXml;
import goby.codegen.plugin.DbMtPlugin;
import goby.codegen.plugin.PluginExecutor;
import goby.codegen.util.VelocityUtil;
import goby.jdbc.ConnectionFactory;
import goby.jdbc.DataSource;
import goby.jdbc.IConnectionFactory;
import goby.threading.ThreadStorageException;
import goby.util.Log;
import goby.util.StringUtil;
import org.jline.reader.LineReader;
import vn.locdt.jats.bundle.question.JQuestion;
import vn.locdt.jats.module.shell.context.ContextKey;
import vn.locdt.jats.module.shell.context.ShellRuntimeContext;
import vn.locdt.jats.synergix.addon.db.DatabaseInfo;
import vn.locdt.jats.util.common.FileUtils;
import vn.locdt.jats.util.common.LogUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GobyGeneratorRunner {
	private LineReader lineReader;

	private static final String JDBC_CONFIG_FILE = "jdbc2.properties";
	private static final String DEFAULT_TEMPLATE_XML = "template/template.xml";
	private static final String DEFAULT_PLUGIN_XML = "plugin.xml";
	private static final String VELOCITY_FILE = "velocity.properties";
	public static final String GENERATED_PATH = "generated";

	public GobyGeneratorRunner(LineReader lineReader) {
		this.lineReader = lineReader;
	}

	public void run() throws ThreadStorageException {
		TemplateXml templateXml = new TemplateXml();
		PluginXml pluginXml = new PluginXml();
		String jdbcConfigFile;

		try {
			// load goby config
			String gobyConfigPath = ShellRuntimeContext.getContext(ContextKey.GOBY_CONFIG_PATH, String.class);

			if (gobyConfigPath == null) {
				gobyConfigPath = FileUtils.path(FileUtils.CONFIG_FOLDER_PATH);
				ShellRuntimeContext.addContext(ContextKey.GOBY_CONFIG_PATH, gobyConfigPath);

				ClassLoader cl = GobyGeneratorRunner.class.getClassLoader();
				FileUtils.createFileFromResource(cl, "template", FileUtils.path(gobyConfigPath, "template"));
				FileUtils.createFileFromResource(cl, DEFAULT_PLUGIN_XML, FileUtils.path(gobyConfigPath, DEFAULT_PLUGIN_XML));
				FileUtils.createFileFromResource(cl, VELOCITY_FILE, FileUtils.path(gobyConfigPath, VELOCITY_FILE));
			}

			templateXml.setFileLocation(FileUtils.path(gobyConfigPath, DEFAULT_TEMPLATE_XML));
			pluginXml.setFileLocation(FileUtils.path(gobyConfigPath, DEFAULT_PLUGIN_XML));
			VelocityUtil.initVelocity(FileUtils.path(gobyConfigPath, VELOCITY_FILE));

			LogUtils.printLog("               Goby Generator is written by Ha Duc Loc <haducloc13@gmail.com>");
			LogUtils.printLog("/*---------------------------------------------------------------------------*/");

			Properties configs = new Properties();
			DatabaseInfo info = ShellRuntimeContext.getContext(ContextKey.DATABASE_INFO, DatabaseInfo.class);
			if (info != null) {
				configs.setProperty("driverClass", info.driver);
				configs.setProperty("url", info.url);
				configs.setProperty("userName", info.user);
				configs.setProperty("password", info.password);
			}
			DataSource dataSource = DataSource.createInstance(configs);
			IConnectionFactory connectionFactory = new ConnectionFactory(dataSource);
			connectionFactory.getThreadConnection();

			LogUtils.printLog("Loading Template Configuration (%s).....", templateXml.getFileLocation());

			TemplateXmlParser tplParser = new TemplateXmlParser();
			tplParser.doParse(templateXml.getFileLocation(), templateXml);

			LogUtils.printLog("Loading Plugin Configuration (%s).....", pluginXml.getFileLocation());
			LogUtils.printLog("\nName of databases are separated by ',' (comma)\nTo exit Goby Generator, type 'q' or using Ctrl + C");

			PluginXmlParser pluginParser = new PluginXmlParser();
			pluginParser.doParse(pluginXml.getFileLocation(), pluginXml);

			DbMtPlugin dbMtPlugin = new PostgresPlugin();
			dbMtPlugin.setOrder(-2147483648);
			dbMtPlugin.setConnectionFactory(connectionFactory);
			pluginXml.getPluginList().add(0, dbMtPlugin);

			PluginExecutor pluginExecutor = new PluginExecutor();
			pluginExecutor.setPluginXml(pluginXml);

			while (true) {
				while (true) {
					String tables = JQuestion.input(this.lineReader, "Enter Tables: ").prompt();
					if (tables.toUpperCase().equalsIgnoreCase("R")) {
						templateXml.Reset();
						tplParser.doParse(templateXml.getFileLocation(), templateXml);
						LogUtils.printLog("Re-loading Template Configuration (%s).....", templateXml.getFileLocation());
					}
					else if (tables.equalsIgnoreCase("q")) {
						return;
					}
					else if (!StringUtil.isBlank(tables)) {
						String[] tableNames = tables.split(",");
						Log.logln____();
						int idx = 0;
						int len$ = tableNames.length;

						for (int i$ = 0; i$ < len$; ++i$) {
							String table = tableNames[i$];
							String tableName = StringUtil.trimString(table);
							if (!StringUtil.isBlank(tableName)) {
								tableName = tableName.toUpperCase();
								Object[] var10001 = new Object[2];
								++idx;
								var10001[0] = idx;
								var10001[1] = tableName;
								LogUtils.printLog("[%s] Table %s:", var10001);

								try {
									Entity entity = new Entity();
									entity.setTableName(tableName);
									entity.setDataSource(dataSource);
									pluginExecutor.doPlugin(entity);
									if (entity.getKeyList().isEmpty()) {
										LogUtils.printLog("Table %s has NO KEY!", tableName);
									}

									Task task = new Task();
									task.setEntity(entity);
									task.setTemplateXml(templateXml);
									task.doRun();
									LogUtils.printLog("Done!");
								} catch (Exception var31) {
									var31.printStackTrace();
									Log.error____(var31.getMessage());
								}
							}
						}

						Log.logln____();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			LogUtils.printErrorLog("Can not find goby config path.", e);
		} catch (Exception e) {
			e.printStackTrace();
			LogUtils.printErrorLog("Exception occurs!", e);
		}
	}
}
