package org.howmuch;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import org.w3c.dom.*;

import javax.xml.parsers.*;
import java.io.*;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;
import org.xml.sax.SAXException;

public class AmazonScrapper {
    static WebClient webClient;
    static DocumentBuilder builder;
    static DocumentBuilderFactory factory;
    public static HashMap<Integer, String[]> searchQueries_map = new HashMap<>();
    public static String AMAZON_PREFIX_URL = "https://www.amazon.in/s?k=";

    AmazonScrapper() {
        fillSearchQueries();

        factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }

        // Define and declare basic web browser
        webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setPrintContentOnFailingStatusCode(false);
    }

    public static void fillSearchQueries() {
        System.out.println(Arrays.toString(Main.Topics));
        searchQueries_map.put(0, new String[]{"Televisions", "Mobile Phones", "Laptops", "Iphone", "Macbook", "Refrigerators", "Washing Machines", "Smart Watches", "Gaming Laptops", "Computer Accessories", "GPUs", "Tablets", "Playstation", "Xbox"});
        searchQueries_map.put(1, new String[]{"Mens TShirts", "Formal Suits", "Mens Casual Wear", "Womens Casual Wear", "Womens Formal Wear", "Kids Clothes", "Makeup", "Beauty Products", "Analog Watches", "Earrings", "Necklaces", "Jewellery", "Branded Clothes", "Gold Jewellery", "Shoes"});
        searchQueries_map.put(2, new String[]{"Furniture", "Tape", "Stationary", "Cutlery", "Kitchen Products", "Toothpaste", "Chocolates", "Soaps", "Water Bottles", "Carpets", "Sofa Sets", "Tables and Desks", "Cleaning Products"});
        searchQueries_map.put(3, new String[]{"Gifts", "Car Appliances", "Diwali Lights", "Decoration", "Birthday Decor", "Lenses"});

        for (Map.Entry<Integer, String[]> m : searchQueries_map.entrySet()) {
            System.out.println(m.getKey() + " " + Arrays.toString(m.getValue()));
        }
    }


    public static void scrapAndSave() throws ParserConfigurationException, IOException, SAXException {

        for (Map.Entry<Integer, String[]> topic : searchQueries_map.entrySet()) {
            for (int topic_queries = 0; topic_queries < topic.getValue().length; topic_queries++) {
                for (int page = 1; page < 2; page++) {
                    try {
                        HtmlPage urlHTML = webClient.getPage(AMAZON_PREFIX_URL + topic.getValue()[topic_queries] + "&crid=2JOW4XXQM1KWM&sprefix=" + topic.getValue()[topic_queries] + "%2Caps%2C220&ref=sr_pg_" + page);
                        webClient.getCurrentWindow().getJobManager().removeAllJobs();

                        List<HtmlElement> searchResults_List = urlHTML.getByXPath("//div[@data-component-type='s-search-result']");

                        for (int searchResult = 0; searchResult < searchResults_List.size(); searchResult++) {
                            HtmlDivision divv = (HtmlDivision) searchResults_List.get(searchResult);

                            StringBuilder xmlStringBuilder = new StringBuilder();
                            xmlStringBuilder.append("<?xml version=\"1.0\"?>");
                            xmlStringBuilder.append(divv.asXml());

                            ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes(StandardCharsets.UTF_8));
                            xmlParser(input, DataBaseManager.LOCAL_IMG_FOLDER + '/' + Main.Topics[topic.getKey()].toLowerCase() + '/' + topic.getValue()[topic_queries] + searchResult + ".jpg");
                        }
                    } catch (IOException e) {
                        System.out.println("An error occurred: " + e);
                    }

                }
            }

        }
    }

    public static void xmlParser(ByteArrayInputStream inputFile, String filename) throws ParserConfigurationException, IOException, SAXException {

        Document doc = builder.parse(inputFile);

        NodeList nListimages = doc.getElementsByTagName("img");
        Element eElement3 = (Element) nListimages.item(0);
        String[] images = eElement3.getAttribute("srcset").split(",");
        String url = images[images.length - 1].split(" ")[1];
        System.out.println(eElement3.getAttribute("alt"));
        saveImage(url, filename);
        NodeList nList = doc.getElementsByTagName("span");
        for (int i = 0; i < nList.getLength(); i++) {
            Element eElement = (Element) nList.item(i);
            if (eElement.getAttribute("class").equals("a-size-base-plus a-color-base")) {
                System.out.println(eElement.getTextContent().strip());
            }
            if (eElement.getAttribute("class").equals("a-size-base-plus a-color-base a-text-normal")) {
                System.out.println(eElement.getTextContent().strip());
            }
            if (eElement.getAttribute("class").equals("a-price-whole")) {
                System.out.println(eElement.getTextContent().strip());
            }
        }
    }

    public static void saveImage(String URLst, String filepath) {
        try {
            InputStream in = new URL(URLst).openStream();
            Files.copy(in, Paths.get(filepath));
        } catch (Exception e) {
            System.out.println("could not save image");
        }
    }

}
