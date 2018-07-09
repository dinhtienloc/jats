package vn.locdt.jats.module.generator;

import vn.locdt.jats.module.generator.context.GenerationContext;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by locdt on 2/7/2018.
 */
public abstract class Generator<C extends GenerationContext> {
    private static final Logger log = Logger.getLogger(Generator.class.getName());
    protected String templateName;
    protected Map<String, Object> dataMapping;
    protected C context;

    public Generator() {
        this.dataMapping = new HashMap<>();
    }

    protected void prepareContext() {
        dataMapping.put("context", context);
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public C getContext() {
        return context;
    }

    public void setContext(C context) {
        this.context = context;
    }

    public boolean generate(TemplateProducer producer) {
        String outputDir = context.getRootPackage() + context.getProjectPath();

        String outputName = context.getOutputName();
        String ext = context.getFileType().getExt();

        try {
            String outputPath = "";
            if (outputDir != null && outputDir.length() > 0)
                outputPath += outputDir + File.separator;

            outputPath += outputName + ext;
            Path des = Paths.get(outputPath);

            if (!Files.exists(des)) {
                if (des.getParent() != null)
                    Files.createDirectories(des.getParent());
                Files.createFile(des);
            }

            prepareContext();

            return producer.produce(dataMapping, templateName, des);
        } catch (IOException e) {
            log.log(Level.INFO, "Error create entity file " + outputDir + File.separator + outputName);
        }
        return false;
    }
}
