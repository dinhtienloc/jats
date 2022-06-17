package goby.codegen.config;

import goby.codegen.objectmodel.Pazkage;
import goby.codegen.objectmodel.Template;
import goby.codegen.objectmodel.TemplateXml;
import goby.util.Assert;
import goby.util.FileUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.Iterator;
import java.util.Set;

public class NewTemplateXmlParser extends TemplateXmlParser {

    public void doParse(String xmlConfigPath, String templatePath, TemplateXml templateXml) throws Exception {
        SAXReader reader = new SAXReader();
        Document document = reader.read(xmlConfigPath);
        Element rootEle = document.getRootElement();
        templateXml.setLocation(templatePath);
        templateXml.setDesc(rootEle.attributeValue("desc"));
        Iterator i = rootEle.elementIterator();

        while (true) {
            while (i.hasNext()) {
                Element ele = (Element) i.next();
                String name = ele.getName();
                if ("template".equals(name)) {
                    Template template = this.createTemplate(ele, templateXml.getLocation());
                    templateXml.getTemplateList().add(template);
                } else if ("configs".equals(name)) {
                    this.parseTemplateConfigs(ele, templateXml);
                } else if ("templateGroup".equals(name)) {
                    String groupLocation = FileUtil.joinPath(templateXml.getLocation(), ele.attributeValue("location"));
                    Assert.notBlank____(groupLocation, "Invalid xml definition. TemplateGroup location must be defined");
                    this.parseTemplateGroup(ele, groupLocation, templateXml);
                } else if ("packages".equals(name)) {
                    this.parsePackages(ele, templateXml);
                    Set<String> packageIds = templateXml.getPackageMap().keySet();
                    Iterator i$ = packageIds.iterator();

                    while (i$.hasNext()) {
                        String packageId = (String) i$.next();
                        Pazkage pazkage = templateXml.getPackageMap().get(packageId);
                        pazkage.setValue(this.buildPackageValue(pazkage, templateXml));
                    }
                }
            }

            this.processTemplatePackages(templateXml);
            this.processTemplateInterfaces(templateXml);
            return;
        }
    }
}
