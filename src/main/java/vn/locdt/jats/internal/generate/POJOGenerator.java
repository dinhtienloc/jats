package vn.locdt.jats.internal.generate;

import org.hibernate.tool.hbm2x.POJOExporter;
import vn.locdt.jats.config.SettingData;

import java.io.File;

/**
 * Created by locdt on 1/23/2018.
 */
public class POJOGenerator implements Generator {
    private static POJOExporter exporter = new POJOExporter();

    @Override
    public void generate() {
        exporter.setConfiguration(SettingData.getDatabaseSetting().getHbmConfiguration());
        exporter.setOutputDirectory(new File(SettingData.getEntityDirectoryPath()));
        exporter.setTemplatePath(new String[0]);
        exporter.getProperties().setProperty("ejb3", "true");
        exporter.getProperties().setProperty("jdk5", "true");
        exporter.start();
    }
}
