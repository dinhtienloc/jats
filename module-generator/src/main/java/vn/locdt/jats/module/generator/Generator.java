package vn.locdt.jats.module.generator;

import vn.locdt.jats.module.generator.context.GenerationContext;
import vn.locdt.jats.module.generator.exception.TemplateException;
import vn.locdt.jats.util.common.FileUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by locdt on 2/7/2018.
 */
public abstract class Generator<C extends GenerationContext> {
    private static final Logger log = Logger.getLogger(Generator.class.getName());
    protected String templateName;
    protected Map<String, Object> dataMapping;
    protected C context;

    public Generator(C context) {
        this.context = context;
        this.dataMapping = new HashMap<>();
        this.dataMapping.put("context", context);
    }

    protected void prepareContext() {
        return;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public C getContext() {
        return this.context;
    }

    public void setContext(C context) {
        this.context = context;
    }

    public void generate(TemplateProducer producer) throws IOException, TemplateException {
        Path des = Paths.get(this.context.getOutputPath());
        FileUtils.createFile(des);

        this.prepareContext();
        producer.produce(this.dataMapping, this.templateName, des);
    }
}
