package vn.locdt.jats.module.shell.config;

import org.springframework.core.annotation.Order;
import vn.locdt.jats.module.shell.context.ContextKey;
import vn.locdt.jats.util.common.FileUtils;

import javax.annotation.Priority;

@Order(1)
public class GlobalConfiguration extends Configuration {
	private static final String PATH = FileUtils.path(FileUtils.CONFIG_FOLDER_PATH, FileUtils.CONFIG_FILE_NAME);

	public GlobalConfiguration() {
		super(PATH);
	}

	@Override
	public void saveShellContextToConfig() {
		this.saveConfigFromShellContext(ContextKey.DB_CONFIG);
	}

	@Override
	public void loadConfigToShellContext() {
		this.loadConfigToShellContext(ContextKey.DB_CONFIG);
	}
}
