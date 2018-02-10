package vn.locdt.jats.addon.entity.context;

import vn.locdt.jats.addon.entity.modeling.util.StringUtils;
import vn.locdt.jats.addon.entity.modeling.model.Table;

/**
 * Created by locdt on 2/9/2018.
 */
public class EntityContext extends JavaClassContext {
    private Table model;

    public EntityContext(Table model, String outputDir, String outputName) {
        super(outputDir, outputName);
        this.model = model;
    }

    public EntityContext(Table model) {
        super();
        this.model = model;
    }

    @Override
    public String getClassName() {
        return model.getJavaName();
    }

    @Override
    public String getExtendStatement() {
        return "";
    }

    @Override
    public String getImplementStatement() {
        return "";
    }

    public Table getMappingAttribute() {return this.model;}

    public Table getModel() {
        return model;
    }
}
