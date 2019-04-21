package vn.locdt.jats.th5jpagen.addon.config;

import vn.locdt.jats.module.shell.config.Configuration;
import vn.locdt.jats.module.shell.context.ContextKey;
import vn.locdt.jats.util.common.FileUtils;

public class GobyConfiguration extends Configuration {
	private static final String PATH = FileUtils.path(FileUtils.CONFIG_FOLDER_PATH, FileUtils.CONFIG_FILE_NAME);

	public GobyConfiguration() {
		super(PATH);
	}


	@Override
	public void saveShellContextToConfig() {
		this.saveConfigFromShellContext(ContextKey.GOBY_CONFIG_PATH);
	}

	@Override
	public void loadConfigToShellContext() {
		this.loadConfigToShellContext(ContextKey.GOBY_CONFIG_PATH);
	}
}
