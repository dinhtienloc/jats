package vn.locdt.jats.entity.generator.generator;

import vn.locdt.jats.entity.generator.context.EntityContext;
import vn.locdt.jats.entity.generator.modeling.model.Table;
import vn.locdt.jats.module.generator.Generator;

/**
 * Created by locdt on 2/7/2018.
 */
public class EntityGenerator extends Generator<EntityContext> {

    public EntityGenerator(Table table, String rootPackage, String projectPath, String outputName, String packageName) {
        this.context = new EntityContext(table, rootPackage, projectPath, outputName, packageName);
    }

    public EntityGenerator(Table table) {
        this.context = new EntityContext(table);
    }

    public void setPackageName(String packageName) {
        this.context.setPackageName(packageName);
    }
}
