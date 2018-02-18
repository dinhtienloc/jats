package vn.locdt.jats.addon.entity;

import vn.locdt.jats.addon.entity.generator.EntityGenerator;
import vn.locdt.jats.addon.entity.modeling.DatabaseMetadataWrapper;
import vn.locdt.jats.addon.entity.modeling.DatabaseReaderFactory;
import vn.locdt.jats.addon.entity.modeling.model.Catalog;
import vn.locdt.jats.addon.entity.modeling.system.SystemModeling;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by locdt on 2/9/2018.
 */
public class EntityGeneration {
    public static void main(String[] args) throws Exception{
        DatabaseMetadataWrapper wrapper = createDatabaseMetadataWrapper();
        Catalog catalog = readCatalog("thesis", wrapper);

        TemplateProducer producer = TemplateProducer.createProducer("templates/entity");
        EntityGenerator gen = new EntityGenerator(catalog.findTableByName("thesis_revision"));
        gen.setOutputDir("output/generator");
        gen.setOutputName("test");
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
