package vn.locdt.jats.addon.entity.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import vn.locdt.jats.addon.entity.generator.exception.TemplateException;

import java.io.*;
import java.nio.file.Path;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by locdt on 2/8/2018.
 */
public class TemplateProducer {
    private static final Logger log = Logger.getLogger(TemplateProducer.class.getName());

    private static volatile TemplateProducer producer;
    private String templateFolder;
    private Configuration configuration;

    private TemplateProducer(String templateFolder) {
        try {
            if (producer != null) {
                throw new RuntimeException("TemplateProducer can be initialize only one time. Use getInstance() to get the single instance of this class.");
            } else {
                this.templateFolder = templateFolder;
                configuration = new Configuration(Configuration.VERSION_2_3_23);
                configuration.setDirectoryForTemplateLoading(new File(templateFolder));
                configuration.setDefaultEncoding("UTF-8");
                configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
                configuration.setLogTemplateExceptions(false);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while initializing TemplateProducer", e);
        }
    }

    public static TemplateProducer createProducer(String templateFolder) {
        if (producer == null) {
            synchronized (TemplateProducer.class) {
                if (producer == null) producer = new TemplateProducer(templateFolder);
            }
        }
        return producer;
    }

    public static TemplateProducer getProducer() {
        if (producer == null)
            throw new RuntimeException("TemplateProducer need to be initialize first. Use createProducer() to create new instance.");

        return producer;
    }

    public void produce(Map<String, Object> context, String templateName, Path destination, String fileType) {
        try (FileWriter fw = new FileWriter(destination.toFile())) {
            String result = processTemplate(context, templateName);

            if (result.length() == 0) {
                log.warning("Empty file " + destination + ". Skip creating file!");
                return;
            }

            log.log(Level.INFO, "Writing " + destination + " successfully!");
            fw.write(result);

        } catch (Exception e) {
            throw new TemplateException("Error while writing entity file", e);
        }
    }

    private String processTemplate(Map<String, Object> context, String templateName) throws Exception {
        StringWriter sw = new StringWriter();
        BufferedWriter bw = new BufferedWriter(sw);

        Template template = configuration.getTemplate(templateName);
        template.process(context, bw);

        return sw.toString();
    }

}
