/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawl;

import org.xml.sax.SAXParseException;
import utils.ErrorUtil;

/**
 *
 * @author Khanh
 */
public class MainCrawler {

    public static void main(String[] args) {

        try {
            String url = "https://artpuzzle.vn/shop/";
            ParadigmCrawler productCrawler = new ParadigmCrawler(url);
            productCrawler.crawl();
//            CategoryCrawler categoryCrawler = new CategoryCrawler(url);
//            categoryCrawler.crawl();
        } catch (SAXParseException e) {
            if (e.getMessage().contains("was referenced, but not declared")) {
                ErrorUtil.saveLogErrorSAXEntity(e.getMessage().split("\"")[1]);
            }
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
