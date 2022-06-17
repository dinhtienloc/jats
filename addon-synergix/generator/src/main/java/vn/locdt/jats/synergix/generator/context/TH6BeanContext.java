package vn.locdt.jats.synergix.generator.context;

import vn.locdt.jats.module.generator.context.JavaClassContext;
import vn.locdt.jats.synergix.generator.context.model.SynergixFormModel;

public class TH6BeanContext<M extends SynergixFormModel> extends JavaClassContext<M> {
    public TH6BeanContext(M contextModel, String outputPath, String outputName, String packageName) {
        super(contextModel, outputPath, outputName, packageName);
    }

    public TH6BeanContext(M contextModel) {
        super();
        this.contextModel = contextModel;
    }

    @Override
    public String getClassName() {
        return this.getContextModel().getContextNameAsClassName() + "Bean";
    }

    @Override
    public String getExtendClassName() {
        return this.getContextModel().getParentBeanName();
    }

    @Override
    public String getImplementClassName() {
        return null;
    }

    @Override
    public String toString() {
        return "TH6BeanContext{" +
                "contextModel=" + contextModel +
                ", outputName='" + outputName + '\'' +
                ", fileType=" + fileType +
                ", outputDirectory='" + outputDirectory + '\'' +
                '}';
    }
}
