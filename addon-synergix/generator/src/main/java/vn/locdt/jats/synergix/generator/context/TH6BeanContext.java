package vn.locdt.jats.synergix.generator.context;

import vn.locdt.jats.module.generator.context.JavaClassContext;
import vn.locdt.jats.synergix.generator.context.model.FormModel;

public class TH6BeanContext extends JavaClassContext<FormModel> {
    public TH6BeanContext(FormModel contextModel, String rootPackage, String projectPath, String outputName, String packageName) {
        super(contextModel, rootPackage, projectPath, outputName, packageName);
    }

    public TH6BeanContext(FormModel contextModel) {
        super();
        this.contextModel = contextModel;
    }

    @Override
    public String getClassName() {
        return this.getContextModel().getContextName() + "Bean";
    }

    @Override
    public String getExtendClassName() {
        return this.getContextModel().getParentBeanName();
    }

    @Override
    public String getImplementClassName() {
        return null;
    }
}
