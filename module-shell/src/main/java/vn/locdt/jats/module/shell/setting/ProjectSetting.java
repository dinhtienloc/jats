package vn.locdt.jats.module.shell.setting;

import vn.locdt.jats.module.shell.constants.PropertiesConstants;
import vn.locdt.jats.util.common.FileUtils;
import vn.locdt.jats.util.common.LogUtils;

import java.nio.file.Path;
import java.util.Properties;

/**
 * Created by locdt on 1/23/2018.
 */
public class ProjectSetting extends Setting {
    protected String rootPackage;
    protected String entityFolder;
    protected Path rootPackagePath;

    public ProjectSetting() {
        super();
    }

    public ProjectSetting(Properties prop) {
        this.entityFolder = prop.getProperty(PropertiesConstants.ENTITYFOLDER);
        this.rootPackage = prop.getProperty(PropertiesConstants.ROOTPACKAGE);
        findRootPackagePath(rootPackage);
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

    public Path getRootPackagePath() {
        return rootPackagePath;
    }

    public void setRootPackagePath(Path rootPackagePath) {
        this.rootPackagePath = rootPackagePath;
    }

    public boolean findRootPackagePath(String rootPackage) {
        boolean found = false;
        Path pkgPath = FileUtils.findFileWithPackageName(rootPackage);
        if (pkgPath == null) {
            LogUtils.printErrorLog("Package '" + rootPackage + "' does not exist.");
        } else {
            LogUtils.printLog("Package '" + rootPackage + "' found.");
            found = true;
        }

        this.rootPackagePath = pkgPath;
        return found;
    }
}
