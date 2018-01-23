package vn.locdt.jats.config;

import org.apache.commons.lang.SystemUtils;
import vn.locdt.jats.constants.PropertiesConstants;
import vn.locdt.jats.util.Utils;

import java.io.*;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Created by locdt on 1/22/2018.
 */
public class ConfigurationData {
    private static ProjectConfiguration projectConfig = null;
    private static DatabaseConfiguration dbConfig = null;
    private static Properties properties;

    static {
        properties = new Properties();
    }

    public static ProjectConfiguration getProjectConfiguration() {
        return projectConfig;
    }

    public static DatabaseConfiguration getDatabaseConfiguration() {
        return dbConfig;
    }

    public static void loadConfiguration() {
        Properties properties = getConfigProperties();
        if (properties != null) {
            dbConfig = new DatabaseConfiguration(properties);
            projectConfig = new ProjectConfiguration(properties);
        }
    }

    private static Properties getConfigProperties() {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(Utils.getConfigurationPath())) {
            // load a properties file
            properties.load(input);
            return properties;
        } catch (Exception e) {
            return null;
        }
    }

    public static void save() {
        try {
            // Project properties
            properties.setProperty(PropertiesConstants.ROOTPACKAGE, projectConfig.getRootPackage());

            // Database properties
            properties.setProperty(PropertiesConstants.DBTYPE, dbConfig.getDbType());
            properties.setProperty(PropertiesConstants.DBURL, dbConfig.getDbUrl());
            properties.setProperty(PropertiesConstants.DBUSER, dbConfig.getDbUser());
            properties.setProperty(PropertiesConstants.DBPASS, dbConfig.getDbPass());

            properties.store(new FileOutputStream(Utils.getConfigurationPath()), null);
        } catch (IOException e) {
            System.out.println("Error when saving configuration setting.");
        }
    }
}
