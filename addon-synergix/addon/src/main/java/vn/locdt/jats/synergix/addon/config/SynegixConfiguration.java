package vn.locdt.jats.synergix.addon.config;

import vn.locdt.jats.module.shell.config.Configuration;
import vn.locdt.jats.module.shell.context.ContextKey;
import vn.locdt.jats.synergix.addon.db.DatabaseInfo;
import vn.locdt.jats.synergix.addon.db.DatabaseLoader;
import vn.locdt.jats.util.common.FileUtils;
import vn.locdt.jats.util.common.LogUtils;

public class SynegixConfiguration extends Configuration {
	private static final String PATH = FileUtils.path(FileUtils.CONFIG_FOLDER_PATH, FileUtils.CONFIG_FILE_NAME);

	public SynegixConfiguration() {
		super(PATH);
	}

	@Override
	public void saveShellContextToConfig() {
		this.saveConfigFromShellContext(ContextKey.DATABASE_INFO);
		this.saveConfigFromShellContext(ContextKey.TH6_PATH);
	}

	@Override
	public void loadConfigToShellContext() {
		this.loadConfigToShellContext(ContextKey.TH6_PATH);

		String dbAlias = this.properties.getProperty(ContextKey.DATABASE_INFO.getPropKey());
		DatabaseInfo info = DatabaseLoader.getInfo(dbAlias);
		if (info != null) {
			info.getConnection();
			this.loadConfigToShellContext(ContextKey.DATABASE_INFO, info);
			LogUtils.printSuccessLog("Connect successfully!");
		}
	}
}
