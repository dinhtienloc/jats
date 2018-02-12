package vn.locdt.jats.addon.entity.context;

import vn.locdt.jats.addon.entity.modeling.model.Table;

/**
 * Created by locdt on 2/9/2018.
 */
public class EntityContext extends JavaClassContext {
    private Table contextModel;

    public EntityContext(Table contextModel, String outputDir, String outputName) {
        super(outputDir, outputName);
        this.contextModel = contextModel;
    }

    public EntityContext(Table contextModel) {
        super();
        this.contextModel = contextModel;
    }

    @Override
    public String getClassName() {
        return contextModel.getJavaName();
    }

    @Override
    public String getExtendStatement() {
        return "";
    }

    @Override
    public String getImplementStatement() {
        return "";
    }

    public Table getContextModel() {
        return contextModel;
    }
}
