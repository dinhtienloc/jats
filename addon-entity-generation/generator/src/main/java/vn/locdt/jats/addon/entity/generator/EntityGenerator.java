package vn.locdt.jats.addon.entity.generator;

import vn.locdt.jats.addon.entity.context.EntityContext;
import vn.locdt.jats.addon.entity.modeling.model.Table;

/**
 * Created by locdt on 2/7/2018.
 */
public class EntityGenerator extends Generator<Table, EntityContext> {

    public EntityGenerator(Table table, String outputDir, String outputName) {
        this(table);
        this.context.setOutputDir(outputDir);
        this.context.setOutputName(outputName);
    }

    public EntityGenerator(Table table) {
        super(table);
        this.context = new EntityContext(table);
        this.dataMapping.put("context", context);
    }

    public void setPackageName(String packageName) {
        this.context.setPackageName(packageName);
    }
}
