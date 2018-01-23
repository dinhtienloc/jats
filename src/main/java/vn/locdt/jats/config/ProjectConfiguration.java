package vn.locdt.jats.config;

import vn.locdt.jats.constants.PropertiesConstants;

import java.util.Properties;

/**
 * Created by locdt on 1/23/2018.
 */
public class ProjectConfiguration extends Configuration {
    protected String rootPackage;

    public ProjectConfiguration() {super();}

    public ProjectConfiguration(Properties prop) {
        this.rootPackage = prop.getProperty(PropertiesConstants.ROOTPACKAGE);
    }

    public String getRootPackage() {
        return rootPackage;
    }

    public void setRootPackage(String rootPackage) {
        this.rootPackage = rootPackage;
    }
}
