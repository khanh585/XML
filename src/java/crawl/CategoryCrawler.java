/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawl;

import daos.CategoryDAO;
import handler.CategoryHandler;
import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import utils.crawler.UtilsCrawler;

/**
 *
 * @author Khanh
 */
public class CategoryCrawler {

    private String webURL;
    private SAXParserFactory spf;
    private SAXParser sax;
    private CategoryDAO dao;
    private CategoryHandler handler;

    public CategoryCrawler(String webURL) throws ParserConfigurationException, SAXException, ClassNotFoundException, SQLException {
        this.webURL = webURL;
        spf = SAXParserFactory.newInstance();
        sax = spf.newSAXParser();
        dao = new CategoryDAO();
    }

    public void crawl() throws ClassNotFoundException, SQLException, SAXException, IOException {
            CategoryHandler handler = new CategoryHandler(dao);
            UtilsCrawler crawler = new UtilsCrawler("category.html", webURL, "<ul class=\"sub-menu mega-menu-container");
            crawler.saveWebContentToFile();
            sax.parse(new InputSource(new StringReader(crawler.readFileXML())), handler);
    }
}
