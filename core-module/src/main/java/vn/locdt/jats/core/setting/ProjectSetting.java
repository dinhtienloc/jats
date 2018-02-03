package vn.locdt.jats.core.setting;

import vn.locdt.jats.core.constants.PropertiesConstants;

import java.util.Properties;

/**
 * Created by locdt on 1/23/2018.
 */
public class ProjectSetting extends Setting {
    protected String rootPackage;
    protected String entityFolder;

    public ProjectSetting() {super();}

    public ProjectSetting(Properties prop) {
        this.rootPackage = prop.getProperty(PropertiesConstants.ROOTPACKAGE);
    }

    public String getRootPackage() {
        return rootPackage;
    }

    public void setRootPackage(String rootPackage) {
        this.rootPackage = rootPackage;
    }

    public String getEntityFolder() {
        return entityFolder;
    }

    public void setEntityFolder(String entityFolder) {
        this.entityFolder = entityFolder;
    }
}
