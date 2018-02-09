package vn.locdt.jats.addon.entity.generator;

import vn.locdt.jats.addon.entity.StringUtils;
import vn.locdt.jats.addon.entity.context.GenerationContext;
import vn.locdt.jats.addon.entity.generator.exception.GeneratorException;
import vn.locdt.jats.addon.entity.modeling.model.Model;

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
public abstract class Generator {
    private static final Logger log = Logger.getLogger(Generator.class.getName());
    protected Model model;
    protected String templateName;
    protected Map<String, Object> dataMapping;
    protected GenerationContext context;

    public Generator() {
        this.dataMapping = new HashMap<>();
    }

    public Generator(Model model) {
        this.model = model;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public GenerationContext getContext() {
        return context;
    }

    public void setContext(GenerationContext context) {
        this.context = context;
    }

    public void setOutputDir(String dir) {
        this.context.setOutputDir(dir);
    }

    public void setOutputName(String name) {
        this.context.setOutputName(name);
    }

    public void generate(TemplateProducer producer) {
        String outputDir = context.getOutputDir();

        if (StringUtils.containPackageInvalidCharacters(outputDir))
            throw new GeneratorException("'" + outputDir + "' is an invalid directory name.");

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

            producer.produce(dataMapping, templateName, des, ext);
        } catch (IOException e) {
            log.log(Level.INFO, "Error create entity file " + outputDir + File.separator + outputName);
        }
    }
}
