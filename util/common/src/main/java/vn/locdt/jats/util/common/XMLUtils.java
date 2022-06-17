package vn.locdt.jats.util.common;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLUtils {
    public static Document newDocument() {
        try {
            DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            return doc;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Document parseFile(File file) {
        try {
            DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = docBuilder.parse(file);
            doc.getDocumentElement().normalize();
            return doc;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void writeXML(Document doc, Writer writer) {
        try {
            //set up a transformer
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            //trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            StreamResult result = new StreamResult(writer);
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }


    public static Map<String, List<Element>> mapChildElements(Element e) {
        Map<String, List<Element>> childElements = new HashMap<String, List<Element>>();

        for (Node child = e.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                Element childElement = (Element) child;
                List<Element> list = childElements.get(childElement.getTagName());
                if (list == null) {
                    list = new ArrayList<Element>();
                    childElements.put(childElement.getTagName(), list);
                }

                list.add(childElement);
            }
        }

        return childElements;
    }


}
