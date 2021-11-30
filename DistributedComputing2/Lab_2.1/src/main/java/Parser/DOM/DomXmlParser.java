package Parser.DOM;

import Parser.XmlFileValidator;
import Werehouse.Section;
import Werehouse.Werehouse;
import Werehouse.Product;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class DomXmlParser {
  private static final String pathToValidator = "src/main/resources/Werehouse.xsd";
  private Logger logger = Logger.getLogger(Werehouse.class.getName());
  private Werehouse werehouse;

  public DomXmlParser(University werehouse) {
    this.werehouse = werehouse;
  }

  public void loadXMLDocument(String pathToXML) throws Exception {
    DocumentBuilderFactory dbf = null;
    DocumentBuilder documentBuilder = null;
    try {
      dbf = DocumentBuilderFactory.newInstance();
      documentBuilder = dbf.newDocumentBuilder();
    } catch (ParserConfigurationException e) {
      logger.info(e.getMessage());
    }
    Document document = null;
    try {
      document = documentBuilder.parse(new File(pathToXML));
    } catch (SAXException e) {
      logger.info(e.getMessage());
    } catch (IOException e) {
      logger.info(e.getMessage());
    }

    Element root = document.getDocumentElement();

    if (root.getTagName().equals("werehouse")) {
      NodeList listSections = root.getElementsByTagName("section");
      for (int i = 0; i < listSections.getLength(); i++) {
        Element section = (Element) listSections.item(i);
        String sectionName = section.getAttribute("name");
        String sectionType = section.getAttribute("type");
        Group werehouseSection = new Section(sectionName, Integer.parseInt(sectionType));
        werehouse.addSection(werehouseSection);
        NodeList listProducts = section.getElementsByTagName("product");
        for (int j = 0; j < listProducts.getLength(); j++) {
          Element product = (Element) listProducts.item(j);
          String name = product.getAttribute("name");
          String price = product.getAttribute("price");
          werehouse.addProduct(werehouseSection, name, Integer.parseInt(price));
        }
      }
    }

  public void saveXMLDocument(String pathToXML) {
    DocumentBuilderFactory dbf = null;
    DocumentBuilder documentBuilder = null;
    Document document = null;
    dbf = DocumentBuilderFactory.newInstance();
    try {
      documentBuilder = dbf.newDocumentBuilder();
    } catch (ParserConfigurationException e) {
      logger.info(e.getMessage());
    }
    document = documentBuilder.newDocument();

    Element root = document.createElement("werehouse");
    document.appendChild(root);

    ArrayList<Section> sections = werehouse.getSections();
    for (Section section : sections) {
      Element sectionElem = document.createElement("section");
      sectionElem.setAttribute("name", section.getName());
      sectionElem.setAttribute("type", String.valueOf(section.getType()));
      root.appendChild(sectionElem);
      ArrayList<Product> sectionProduct = werehouse.getProducts(section.getName());
      for (Product product : sectionProduct) {
        Element productElem = document.createElement("product");
        productElem.setAttribute("name", product.getName());
        productElem.setAttribute("price", String.valueOf(product.getPrice()));
        sectionElem.appendChild(productElem);
      }
    }

    Transformer tr = null;
    try {
      tr = TransformerFactory.newInstance().newTransformer();
    } catch (TransformerConfigurationException e) {
      logger.info(e.getMessage());
    }
    tr.setOutputProperty(OutputKeys.INDENT, "yes");
    tr.setOutputProperty(OutputKeys.METHOD, "xml");
    tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
    tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

    try {
      tr.transform(new DOMSource(document),
                   new StreamResult(new FileOutputStream(pathToXML)));
    } catch (TransformerException e) {
      logger.info(e.getMessage());
    } catch (FileNotFoundException e) {
      logger.info(e.getMessage());
    }

  }
}
