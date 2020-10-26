/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawl;

import daos.ParadigmDAO;
import handler.ParadigmHandler;
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
public class ParadigmCrawler {
    private String webURL;
    private SAXParserFactory spf;
    private SAXParser sax;
    private ParadigmDAO dao;
    private ParadigmHandler handler;
    


    public ParadigmCrawler(String webURL) throws ParserConfigurationException, SAXException, ClassNotFoundException, SQLException {
        this.webURL = webURL;
        spf = SAXParserFactory.newInstance();
        sax = spf.newSAXParser();
        dao = new ParadigmDAO();
    }

    public void crawl() throws ClassNotFoundException, SQLException, SAXException, IOException  {
            ParadigmHandler handler = new ParadigmHandler(dao);
            UtilsCrawler crawler = new UtilsCrawler("product.txt", webURL, "<ul class=\"products");
            crawler.saveWebContentToFile();
            sax.parse(new InputSource(new StringReader(crawler.readFileXML())), handler);
    }
}
