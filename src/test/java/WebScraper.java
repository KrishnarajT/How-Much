import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.w3c.dom.*;

import javax.annotation.processing.Filer;
import javax.xml.parsers.*;
import java.io.*;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLImageElement;
import org.xml.sax.SAXException;

public class WebScraper {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();


        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setPrintContentOnFailingStatusCode(false);

        for (int j = 1; j < 11; j++) {


            try {
                HtmlPage page = webClient.getPage("https://www.amazon.in/s?k=Televisions&i=electronics&rh=n%3A1389396031%2Cp_36%3A14076164031&dc&page=2&crid=3QYQXXW3MO0N2&qid=1666202255&rnid=14076159031&sprefix=mobile+phone%2Caps%2C310&ref=sr_pg_" + j);


                webClient.getCurrentWindow().getJobManager().removeAllJobs();
                webClient.close();

////            prints all the text or some thing
//            List<DomElement> firstHeading = page.getElementsByIdAndOrName("search");
//            System.out.print(firstHeading.get(0).asNormalizedText()); // prints Jsoup

//            List<?> achors = page.getByXPath("//div[@class=\"a-section a-spacing-none a-spacing-top-micro\"]");
//            List<HtmlElement> achors = page.getByXPath("//div[@class=\"s-main-slot s-result-list s-search-results sg-row\"]");
                List<HtmlElement> achors = page.getByXPath("//div[@data-component-type='s-search-result']");
                System.out.println(achors.size());
                FileWriter recipesht = new FileWriter("recipes.xml", true);
                File readfile = new File("recipes.xml");

                for (int a = 0; a < achors.size(); a++) {
                    HtmlDivision divv = (HtmlDivision) achors.get(a);
                    String xml_stuff = divv.asXml();
                    StringBuilder xmlStringBuilder = new StringBuilder();
                    xmlStringBuilder.append("<?xml version=\"1.0\"?>");
                    xmlStringBuilder.append(divv.asXml());
                    ByteArrayInputStream input = new ByteArrayInputStream(
                            xmlStringBuilder.toString().getBytes(StandardCharsets.UTF_8));
                    xmlparsing.parserr(input, "./" + a + j + ".jpg");

//                System.out.println(divv.asXml());
//                recipesht.write(divv.asXml());
//                HtmlImage images = achors.get(a).getFirstByXPath("//img[@class='s-image']");
//                HtmlSpan brandnames = (achors.get(a).getFirstByXPath("//span[@class=\"a-size-base-plus a-color-base\"]"));
//                HtmlSpan itemAnchor = (achors.get(a).getFirstByXPath("//span[@class=\"a-size-base-plus a-color-base a-text-normal\"]"));
//                HtmlSpan prices = (achors.get(a).getFirstByXPath("//span[@class=\"a-price-whole\"]"));
////
//                System.out.println(images.asXml());
//                System.out.println(brandnames.asNormalizedText());
//                System.out.println(itemAnchor.asNormalizedText());
//                System.out.println(prices.asNormalizedText());

//                FileWriter recipesht = new FileWriter("recipes.xml", false);
//                recipesht.write(a.asXml());
////                System.out.println(a.asXml());
//                List<HtmlImage> images = a.getByXPath("//img[@class='s-image']");
////                System.out.println(i.asXml());
////                System.out.println(a.asNormalizedText());
//
////                for (HtmlImage ita : images)
////                {
////                    System.out.println(ita.asXml());
////                }
//                List<HtmlSpan> brandnames = (a.getByXPath("//span[@class=\"a-size-base-plus a-color-base\"]"));
//                List<HtmlSpan> itemAnchor = (a.getByXPath("//span[@class=\"a-size-base-plus a-color-base a-text-normal\"]"));
//                List<HtmlSpan> prices = (a.getByXPath("//span[@class=\"a-price-whole\"]"));
//
//                System.out.println(brandnames.size());
//                System.out.println(itemAnchor.size());
//                System.out.println(prices.size());
//                for (int j = 0; j < brandnames.size(); j++) {
//                    images.get(j).getFirstByXPath("@alt");
//                    System.out.println(brandnames.get(j).asNormalizedText());
//                    System.out.println(itemAnchor.get(j).asNormalizedText());
//                    System.out.println(prices.get(j).asNormalizedText());
//                    System.out.println();
//                }
//                for (HtmlSpan itb : brandnames)
//                {
//                    System.out.println(itb.asNormalizedText());
//                }
//                for (HtmlSpan ita : itemAnchor)
//                {
//                    System.out.println(ita.asNormalizedText());
//                }
//                for (HtmlSpan itp : prices)
//                {
//                    System.out.println(itp.asNormalizedText());
//                }
//                HtmlElement spanPrice = ((HtmlElement) a.getFirstByXPath(".//a/span[@class='result-price']")) ;
//                System.out.println(itemAnchor.asXml());
                }
//            xmlparsing.parserr(readfile);

//                                    <span class="a-size-base-plus a-color-base a-text-normal">

//            recipesFile.close();
//            String title = page.getTitleText();
//            System.out.println("Page Title: " + title);
//            FileWriter recipesht = new FileWriter("recipes.html", false);
//            System.out.println(page.getElementsByIdAndOrName("div"));
//            List<?> anchors = page.getByXPath("//div[@class='a-section a-spacing-none a-spacing-top-micro']");
//            List<HtmlElement> items = page.getByXPath("//li[@class='result-row']") ;
//            recipesht.write(items.toString());
//            HtmlPage something = page.getHtmlPageOrNull();
//
//            HTMLDivElement someelement = page.getFirstByXPath("//div[@class=\"a-section a-spacing-none a-spacing-top-micro\"]");
//            System.out.println(someelement.toString());
//            FileWriter recipesFile = new FileWriter("recipes.csv", false);
//            recipesFile.write("id,name,link\n");
//            for (int i = 0; i < anchors.size(); i++) {
//                HtmlAnchor link = (HtmlAnchor) anchors.get(i);
//                String recipeTitle = link.getAttribute("title").replace(',', ';');
//                String recipeLink = link.getHrefAttribute();
//
//                recipesFile.write(i + "," + recipeTitle + "," + recipeLink + "\n");
//            }
//            recipesFile.close();
//            recipesht.close();

            } catch (IOException e) {
                System.out.println("An error occurred: " + e);
            }


        }
    }
}
//class="a-price" class="a-offscreen"
//
//public class FetchAllBooks {
//
//    public static void main(String[] args) throws IOException {
//
//        WebDriver driver = new FirefoxDri1ver();
//        driver.navigate().to("http://www.amazon.com/tag/center%20right?ref_=tag_dpp_cust_itdp_s_t&store=1");
//
//        List<WebElement> allAuthors =  driver.findElements(By.className("tgProductAuthor"));
//        List<WebElement> allTitles =  driver.findElements(By.className("tgProductTitleText"));
//        int i=0;
//        String fileText = "";
//
//        for (WebElement author : allAuthors){
//            String authorName = author.getText();
//            String Url = (String)((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML;", allTitles.get(i++));
//            final Pattern pattern = Pattern.compile("title=(.+?)>");
//            final Matcher matcher = pattern.matcher(Url);
//            matcher.find();
//            String title = matcher.group(1);
//            fileText = fileText+authorName+","+title+"\n";
//        }
//
//        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("books.csv"), "utf-8"));
//        writer.write(fileText);
//        writer.close();
//
//        driver.close();
//    }
//}