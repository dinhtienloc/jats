package internal.generate;

import internal.database.DatabaseReader;
import org.hibernate.tool.hbm2x.POJOExporter;

/**
 * Created by locdt on 1/21/2018.
 */
public class POJOGenerator implements Generator {
    private POJOExporter exporter;
    private String tableName;
    private static final String POJO_PACKAGE = "model";

    public POJOGenerator(String tableName) {
        this.tableName = tableName;

        exporter = new POJOExporter();
//        exporter.s
//        exporter.setOutputDirectory(outputDir);
        exporter.setTemplatePath(new String[0]);
        exporter.getProperties().setProperty("ejb3", "true");
        exporter.getProperties().setProperty("jdk5", "true");
    }

    @Override
    public void generate() {
        System.out.println("Generated " + tableName);
    }
}
