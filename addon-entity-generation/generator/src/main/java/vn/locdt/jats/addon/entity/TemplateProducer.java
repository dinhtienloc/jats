package vn.locdt.jats.addon.entity;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import vn.locdt.jats.addon.entity.generator.exception.TemplateException;
import vn.locdt.jats.util.LogUtils;

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
//                File f = new File("");
//                LogUtils.printDebugLog(getClass().getResourceAsStream(""));
//                configuration.setDirectoryForTemplateLoading(f);
                configuration.setClassForTemplateLoading(this.getClass(), templateFolder);
                configuration.setDefaultEncoding("UTF-8");
                configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
                configuration.setLogTemplateExceptions(false);
            }
        } catch (Exception e) {
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

    public boolean produce(Map<String, Object> context, String templateName, Path destination) {
        boolean result = false;
        try (FileWriter fw = new FileWriter(destination.toFile())) {
            String content = processTemplate(context, templateName);

            if (content.length() == 0) {
                LogUtils.printWarningLog("Creating " + destination.toAbsolutePath() + " unsuccessfully!");
                return result;
            }

            LogUtils.printSuccessLog("Creating " + destination.toAbsolutePath() + " successfully!");
            fw.write(content);
            return result;
        } catch (Exception e) {
            throw new TemplateException("Error while creating entity file", e);
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
