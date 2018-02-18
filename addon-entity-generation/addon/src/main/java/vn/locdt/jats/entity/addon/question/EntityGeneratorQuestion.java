package vn.locdt.jats.entity.addon.question;

import vn.locdt.jats.addon.entity.generator.EntityGenerator;
import vn.locdt.jats.addon.entity.TemplateProducer;
import vn.locdt.jats.addon.entity.modeling.DatabaseMetadataWrapper;
import vn.locdt.jats.addon.entity.modeling.DatabaseReaderFactory;
import vn.locdt.jats.addon.entity.modeling.model.Catalog;
import vn.locdt.jats.addon.entity.modeling.model.Table;
import vn.locdt.jats.addon.entity.modeling.system.SystemModeling;
import vn.locdt.jats.bundle.question.JQuestion;
import vn.locdt.jats.entity.addon.internal.DatabaseReader;
import vn.locdt.jats.module.shell.question.QuestionCLI;
import vn.locdt.jats.module.shell.question.QuestionStatus;
import vn.locdt.jats.module.shell.setting.SettingData;
import vn.locdt.jats.util.LogUtils;

import java.nio.file.Path;
import java.sql.Connection;
import java.util.List;

/**
 * Created by locdt on 1/22/2018.
 */
public class EntityGeneratorQuestion extends QuestionCLI {
    private DatabaseReader dbReader;

    @Override
    protected void run() {
        Connection connection = SettingData.getDatabaseSetting().getConnection();
        dbReader = new DatabaseReader(connection);

        try {
            DatabaseMetadataWrapper wrapper = new DatabaseMetadataWrapper(connection.getMetaData());
            SystemModeling reader = DatabaseReaderFactory.createSystemReader("MySQL", wrapper);
            TemplateProducer producer = TemplateProducer.createProducer("templates/entity");
            Path rootPackagePath = SettingData.getProjectSetting().getRootPackagePath();

            if (rootPackagePath == null) {
                LogUtils.createErrorLog("Root package is null");
            }

            String chosenCatalog = askForCatalogName();
            if (chosenCatalog == null) {
                LogUtils.createErrorLog("Could not handle this answer: " + chosenCatalog);
                status = QuestionStatus.STOP;
                return;
            }

            LogUtils.printLog("Reading catalog...");
            reader.setCatalog(chosenCatalog);
            Catalog catalog = reader.model();

            String chosenTable = askForTableName(chosenCatalog);
            if (chosenTable == null) {
                LogUtils.createErrorLog("Could not handle this answer: " + chosenTable);
                status = QuestionStatus.STOP;
                return;
            }

            Table table = catalog.findTableByName(chosenTable);
            EntityGenerator gen = new EntityGenerator(table);
            gen.setTemplateName("Entity.ftl");
            gen.setOutputDir(rootPackagePath.toAbsolutePath().toString());
            gen.setOutputName(table.getJavaName());
            boolean result = gen.generate(producer);
            if (result)
                status = QuestionStatus.CONTINUE;
            else
                status = QuestionStatus.STOP;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String askForTableName(String catalogName){
        String chosenTable = null;
        List<String> tables = dbReader.getDatabaseTableList(catalogName);

        if (tables != null) {
            if (tables.size() == 0)
                System.out.println("There is no table in database.");
            else
                chosenTable = JQuestion.select("Choose table?", "table", tables).getValue();
        }
        return chosenTable;
    }

    private String askForCatalogName(){
        String chosenCatalog = null;
        List<String> catalogs = dbReader.getCatalogList();

        if (catalogs != null) {
            if (catalogs.size() == 0)
                System.out.println("There is no catalogs in database.");
            else
                chosenCatalog = JQuestion.select("Choose catalog?", "table", catalogs).getValue();
        }
        return chosenCatalog;
    }
}
