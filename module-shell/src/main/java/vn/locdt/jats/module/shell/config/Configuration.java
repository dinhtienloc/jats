package vn.locdt.jats.module.shell.config;

import org.springframework.core.annotation.Order;
import vn.locdt.jats.module.shell.context.ContextKey;
import vn.locdt.jats.module.shell.context.EnviromentKey;
import vn.locdt.jats.module.shell.context.ShellRuntimeContext;
import vn.locdt.jats.util.common.FileUtils;
import vn.locdt.jats.util.common.LogUtils;
import vn.locdt.jats.util.common.ReflectionUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public abstract class Configuration {
    protected Properties properties;
    protected String configPath;

    public Configuration(String configFileName) {
        this.configPath = FileUtils.path(FileUtils.getJarFileLocation(), configFileName);
        this.properties = new Properties();
        boolean loaded = this.loadConfigFromFile();
        if (loaded) {
            this.loadConfigToShellContext();
        }
    }

    public abstract void saveShellContextToConfig();

    public abstract void loadConfigToShellContext();

    public static void loadAllConfigurations() {
        List<Class<? extends Configuration>> subTypes = new ArrayList<>(ReflectionUtils.getSubTypesOf(Configuration.class, "vn.locdt.jats"));
        subTypes.sort((t1, t2) -> {
            Order p1 = t1.getAnnotation(Order.class);
            Order p2 = t2.getAnnotation(Order.class);
            return (p2 == null ? 0 : p2.value()) - (p1 == null ? 0 : p1.value());
        });

        Set<Configuration> configSet = new HashSet<>();
        for (Class<? extends Configuration> c : subTypes) {
            Configuration config = loadConfiguration(c);
            configSet.add(config);
        }

        ShellRuntimeContext.addEnviromentProp(EnviromentKey.CONFIGURATION, configSet);
    }

    private static <T extends Configuration> T loadConfiguration(Class<T> type) {
        return ReflectionUtils.newInstanceFromClass(type);
    }

    @SuppressWarnings("unchecked")
    public static void saveAllConfiguration() {
        Set<Configuration> configSet = (Set<Configuration>) ShellRuntimeContext.getEnviromentProp(EnviromentKey.CONFIGURATION);
        try {
            for (Configuration config : configSet) {
                config.save();
            }

            LogUtils.printErrorLog("Config saved!");
        } catch (IOException e) {
            LogUtils.printErrorLog("Error when saving configuration setting.");
        }
    }

    private boolean loadConfigFromFile() {
//		System.out.println(this.configPath);
        File propertiesFile = new File(this.configPath);
        if (!propertiesFile.exists()) {
//			LogUtils.printWarningLog("There is no current config!");
            return false;
        } else {
            try (InputStream input = new FileInputStream(propertiesFile)) {
                // load a properties file
                this.properties.load(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    private void save() throws IOException {
        if (this.properties == null) {
            this.properties = new Properties();
        }

        this.saveShellContextToConfig();
        File propertiesFile = FileUtils.createFile(this.configPath);
        Properties existProps = new Properties();
        existProps.load(new FileInputStream(propertiesFile));
        existProps.putAll(this.properties);

        existProps.store(new FileOutputStream(this.configPath), null);
    }

    private void setProperty(String key, String prop) {
        if (key == null || prop == null) return;
        this.properties.setProperty(key, prop);
    }

    public void saveConfigFromShellContext(ContextKey contextKey) {
        this.setProperty(contextKey.getPropKey(), ShellRuntimeContext.getContextAsProp(contextKey));
    }

    public void loadConfigToShellContext(ContextKey contextKey) {
        String contextValue = this.properties.getProperty(contextKey.getPropKey());
        if (contextValue != null) {
            this.loadConfigToShellContext(contextKey, contextValue);
        }
    }

    public void loadConfigToShellContext(ContextKey contextKey, Object contextValue) {
        ShellRuntimeContext.addContext(contextKey, contextValue);
        LogUtils.printLog("Load " + contextKey.getPropKey() + ": " + contextValue.toString());
    }
}
