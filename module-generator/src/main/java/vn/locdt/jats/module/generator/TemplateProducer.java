package vn.locdt.jats.module.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import vn.locdt.jats.module.generator.exception.TemplateException;
import vn.locdt.jats.util.common.LogUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.StringWriter;
import java.nio.file.Path;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by locdt on 2/8/2018.
 */
public class TemplateProducer {
    private static final Logger log = Logger.getLogger(TemplateProducer.class.getName());

    private static volatile TemplateProducer producer;
    private String templateFolder;
    private Configuration configuration;

    private TemplateProducer(Class clazz, String templateFolder) {
        try {
            if (producer != null) {
                throw new RuntimeException("TemplateProducer can be initialize only one time. Use getInstance() to get the single instance of this class.");
            } else {
                this.templateFolder = templateFolder;
                this.configuration = new Configuration(Configuration.VERSION_2_3_23);
//                File f = new File("");
//                LogUtils.printDebugLog(getClass().getResourceAsStream(""));
//                configuration.setDirectoryForTemplateLoading(f);
                this.configuration.setClassForTemplateLoading(clazz, templateFolder);
                this.configuration.setDefaultEncoding("UTF-8");
                this.configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
                this.configuration.setLogTemplateExceptions(false);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while initializing TemplateProducer", e);
        }
    }

    public static TemplateProducer createProducer(Class clazz, String templateFolder) {
        if (producer == null) {
            synchronized (TemplateProducer.class) {
                if (producer == null) producer = new TemplateProducer(clazz, templateFolder);
            }
        }
        return producer;
    }

    public static TemplateProducer createProducer(String templateFolder) {
        return createProducer(TemplateProducer.class, templateFolder);
    }

    public static TemplateProducer getProducer() {
        if (producer == null)
            throw new RuntimeException("TemplateProducer need to be initialize first. Use createProducer() to create new instance.");

        return producer;
    }

    void produce(Map<String, Object> context, String templateName, Path destination) throws TemplateException {
        try (FileWriter fw = new FileWriter(destination.toFile())) {
            String content = this.processTemplate(context, templateName);

            if (content.length() == 0) {
                LogUtils.printWarningLog("Creating " + destination.toAbsolutePath() + " unsuccessfully!");
            }

            LogUtils.printSuccessLog("Creating " + destination.toAbsolutePath() + " successfully!");
            fw.write(content);
        } catch (Exception e) {
            throw new TemplateException("Error while creating entity file: " + e.getMessage(), e);
        }
    }

    private String processTemplate(Map<String, Object> context, String templateName) throws Exception {
        StringWriter sw = new StringWriter();
        BufferedWriter bw = new BufferedWriter(sw);

        Template template = this.configuration.getTemplate(templateName);
        template.process(context, bw);

        return sw.toString();
    }

}
