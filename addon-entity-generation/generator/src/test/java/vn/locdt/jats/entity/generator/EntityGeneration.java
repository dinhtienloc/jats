package vn.locdt.jats.entity.generator;

import vn.locdt.jats.entity.generator.generator.EntityGenerator;
import vn.locdt.jats.module.modeling.DatabaseMetadataWrapper;
import vn.locdt.jats.module.modeling.DatabaseReaderFactory;
import vn.locdt.jats.module.modeling.model.Catalog;
import vn.locdt.jats.module.modeling.system.SystemModeling;
import vn.locdt.jats.module.generator.TemplateProducer;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by locdt on 2/9/2018.
 */
public class EntityGeneration {
    public static void main(String[] args) throws Exception{
//        DatabaseMetadataWrapper wrapper = createDatabaseMetadataWrapper();
//        Catalog catalog = readCatalog("thesis", wrapper);

        TemplateProducer producer = TemplateProducer.createProducer("templates/entity");
        EntityGenerator gen = new EntityGenerator(null);
//        gen.setOutputDir("output/generator");
//        gen.setOutputName("test");
        gen.setTemplateName("Entity.ftl");
        gen.generate(producer);
    }

    private static DatabaseMetadataWrapper createDatabaseMetadataWrapper() {
        DatabaseMetadataWrapper wrapper = null;
        try {
            Connection conn = DriverManager.getConnection(TestConstants.DB_URL,
                    TestConstants.DB_USER,
                    TestConstants.DB_PASS);
            wrapper = new DatabaseMetadataWrapper(conn.getMetaData());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wrapper;
    }

    private static Catalog readCatalog(String catalogName, DatabaseMetadataWrapper wrapper) {
        SystemModeling reader = DatabaseReaderFactory.createSystemReader("MySQL", wrapper);
        reader.setCatalog(catalogName);
        return reader.model();
    }
}
