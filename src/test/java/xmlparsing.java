import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class xmlparsing {
    public static void parserr(ByteArrayInputStream inputFile, String filename) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.parse(inputFile);

        Element root = doc.getDocumentElement();
        NodeList nListimages = doc.getElementsByTagName("img");
        Element eElement3 = (Element) nListimages.item(0);
        String[] images = eElement3.getAttribute("srcset").split(",");
        String url = images[images.length - 1].split(" ")[1];
        System.out.println(eElement3.getAttribute("alt"));
        ImageDownloader.di(url, filename);
        NodeList nList = doc.getElementsByTagName("span");
        for (int i = 0; i < nList.getLength(); i++) {
            Element eElement = (Element) nList.item(i);
            if(eElement.getAttribute("class").equals("a-size-base-plus a-color-base")) {
                System.out.println(eElement.getTextContent().strip());
            }
            if(eElement.getAttribute("class").equals("a-size-base-plus a-color-base a-text-normal")) {
                System.out.println(eElement.getTextContent().strip());
            }
            if(eElement.getAttribute("class").equals("a-price-whole")) {
                System.out.println(eElement.getTextContent().strip());
            }
        }
//        for (int i = 0; i < 2; i++) {
//            System.out.println(doc.getElementsByTagName("span"));
//        }
//        System.out.println("Root element :" + doc.getDocumentElement().getAttributes());
    }
}
