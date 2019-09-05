package vn.locdt.jats.module.shell.config;

import com.google.common.io.Files;
import org.springframework.core.annotation.Order;
import vn.locdt.jats.module.shell.context.ContextKey;
import vn.locdt.jats.util.common.FileUtils;
import vn.locdt.jats.util.common.LogUtils;

import javax.annotation.Priority;
import java.io.File;
import java.io.IOException;

@Order(1)
public class GlobalConfiguration extends Configuration {
	private static final String CONFIG_FILE_NAME = FileUtils.CONFIG_FILE_NAME;

	public GlobalConfiguration() {
		super(CONFIG_FILE_NAME);
		try {
			FileUtils.createFile(this.configPath);
		}
		catch (IOException e) {
			LogUtils.printErrorLog("Can not find and create syn.properties at %s", FileUtils.getJarFileLocation());
		}
	}

	@Override
	public void saveShellContextToConfig() {
//		this.saveConfigFromShellContext(ContextKey.DB_CONFIG);
	}

	@Override
	public void loadConfigToShellContext() {
//		this.loadConfigToShellContext(ContextKey.DB_CONFIG);
	}
}
