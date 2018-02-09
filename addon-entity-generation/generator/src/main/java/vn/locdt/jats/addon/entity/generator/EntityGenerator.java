package vn.locdt.jats.addon.entity.generator;

import vn.locdt.jats.addon.entity.context.EntityContext;
import vn.locdt.jats.addon.entity.modeling.model.Table;

/**
 * Created by locdt on 2/7/2018.
 */
public class EntityGenerator extends Generator {
    public EntityGenerator(Table table, String outputDir, String outputName) {
        this(table);
        this.context.setOutputDir(outputDir);
        this.context.setOutputName(outputName);
    }

    public EntityGenerator(Table table) {
        super();
        this.context = new EntityContext(table);
        this.dataMapping.put("entity", context);
    }
}
