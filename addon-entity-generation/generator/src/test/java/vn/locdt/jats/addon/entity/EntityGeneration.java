package vn.locdt.jats.addon.entity;

import vn.locdt.jats.addon.entity.generator.EntityGenerator;
import vn.locdt.jats.addon.entity.generator.TemplateProducer;
import vn.locdt.jats.addon.entity.modeling.DatabaseMetadataWrapper;
import vn.locdt.jats.addon.entity.modeling.DatabaseReaderFactory;
import vn.locdt.jats.addon.entity.modeling.model.Catalog;
import vn.locdt.jats.addon.entity.modeling.system.SystemModeling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

/**
 * Created by locdt on 2/9/2018.
 */
public class EntityGeneration {
    public static void main(String[] args) throws Exception{
        DatabaseMetadataWrapper wrapper = createDatabaseMetadataWrapper();
        List<Catalog> catalogs = readCatalog("sakila", wrapper);

        TemplateProducer producer = TemplateProducer.createProducer("templates/entity");
        EntityGenerator gen = new EntityGenerator(catalogs.get(0).findTableByName("store"));
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

    private static List<Catalog> readCatalog(String catalogName, DatabaseMetadataWrapper wrapper) {
        SystemModeling reader = DatabaseReaderFactory.createSystemReader("MySQL", wrapper);
        reader.setCatalog(catalogName);
        return reader.model();
    }
}
