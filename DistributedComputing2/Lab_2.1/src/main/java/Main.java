import Parser.DOM.DomXmlParser;
import Werehouse.Werehouse;
import java.io.*;

public class Main {
    public static void main(String[]args) throws Exception {
      System.out.println("Started;");

      Werehouse werehouse = new University();
      DomXmlParser xmlParser = new DomXmlParser(werehouse);
      xmlParser.loadXMLDocument("src/main/resources/Werehouse.xml");
      werehouse.addProduct("Musical instruments","Yamaha GB1K","30000");
      werehouse.addSection("Keyboard instruments","Musical instruments");
      werehouse.deleteProduct("Yamaha GB1K");
      xmlParser.saveXMLDocument("src/main/resources/WerehouseNew.xml");

      System.out.println("Finished;");
    }
}
