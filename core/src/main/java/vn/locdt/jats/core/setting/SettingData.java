package vn.locdt.jats.core.setting;

import vn.locdt.jats.core.constants.PropertiesConstants;
import vn.locdt.jats.core.util.Utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
        if (projectSetting == null)
            projectSetting = new ProjectSetting();
        return projectSetting;
    }

    public static DatabaseSetting getDatabaseSetting() {
        if (dbSetting == null)
            dbSetting = new DatabaseSetting();
        return dbSetting;
    }

    public static void loadConfiguration() {
        Properties properties = getConfigProperties();
        Utils.printDebugLog(properties);
        if (properties != null) {
            Utils.printLog("Loading last run setting...");
            dbSetting = new DatabaseSetting(properties);
            projectSetting = new ProjectSetting(properties);

            boolean loaded = dbSetting.loadDriver(dbSetting.getDbType());
            if (!loaded) return;
            dbSetting.createConnection();
        }
    }

    private static Properties getConfigProperties() {
        Properties properties = new Properties();
        Utils.printDebugLog(Utils.getConfigurationPath());
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
            setProperty(PropertiesConstants.ROOTPACKAGE, projectSetting.getRootPackage());

            // Database properties
            setProperty(PropertiesConstants.DBTYPE, dbSetting.getDbType());
            setProperty(PropertiesConstants.DBURL, dbSetting.getDbUrl());
            setProperty(PropertiesConstants.DBUSER, dbSetting.getDbUser());
            setProperty(PropertiesConstants.DBPASS, dbSetting.getDbPass());

            properties.store(new FileOutputStream(Utils.getConfigurationPath()), null);
        } catch (IOException e) {
            Utils.printErrorLog("Error when saving configuration setting.");
        }
    }

    private static void setProperty(String key, String prop) {
        if (key == null || prop == null) return;
        properties.setProperty(key, prop);
    }

    public static String getEntityDirectoryPath() {
        return SettingData.projectSetting.getRootPackage() + "\\" + SettingData.projectSetting.getEntityFolder();
    }

    public static boolean isHbmConfigurationCreated() {
        return false;
    }
}
