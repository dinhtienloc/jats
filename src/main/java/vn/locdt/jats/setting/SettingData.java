package vn.locdt.jats.setting;

import vn.locdt.jats.constants.PropertiesConstants;
import vn.locdt.jats.util.Utils;

import java.io.*;
import java.util.Properties;

/**
 * Created by locdt on 1/22/2018.
 */
public class SettingData {
    private static ProjectSetting projectSetting = new ProjectSetting();
    private static DatabaseSetting dbSetting = new DatabaseSetting();
    private static Properties properties;

    static {
        properties = new Properties();
    }

    public static ProjectSetting getProjectSetting() {
        return projectSetting;
    }

    public static DatabaseSetting getDatabaseSetting() {
        return dbSetting;
    }

    public static void loadConfiguration() {
        Properties properties = getConfigProperties();
        if (properties != null) {
            dbSetting = new DatabaseSetting(properties);
            projectSetting = new ProjectSetting(properties);
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
            properties.setProperty(PropertiesConstants.ROOTPACKAGE, projectSetting.getRootPackage());

            // Database properties
            properties.setProperty(PropertiesConstants.DBTYPE, dbSetting.getDbType());
            properties.setProperty(PropertiesConstants.DBURL, dbSetting.getDbUrl());
            properties.setProperty(PropertiesConstants.DBUSER, dbSetting.getDbUser());
            properties.setProperty(PropertiesConstants.DBPASS, dbSetting.getDbPass());

            properties.store(new FileOutputStream(Utils.getConfigurationPath()), null);
        } catch (IOException e) {
            System.out.println("Error when saving configuration setting.");
        }
    }

    public static String getEntityDirectoryPath() {
        return SettingData.projectSetting.getRootPackage() + "\\" + SettingData.projectSetting.getEntityFolder();
    }

    public static boolean isHbmConfigurationCreated() {
        return SettingData.getDatabaseSetting().getHbmConfiguration() != null;
    }
}
