/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawl;

import daos.NewsDAO;
import daos.ParadigmDAO;
import dtos.ParadigmDTO;
import handler.NewsHandler;
import handler.ParadigmHandler;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Stack;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;
import utils.ErrorUtil;
import static utils.ErrorUtil.readLogErrorSAXEntity;

/**
 *
 * @author Khanh
 */
public class MainCrawler {

    public static void main(String[] args) {
        try {
            String url = "https://artpuzzle.vn/shop/";
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sax = spf.newSAXParser();
//            NewsDAO dao = new NewsDAO();
//            NewsHandler handler = new NewsHandler(dao);
            ParadigmDAO dao = new ParadigmDAO();
            ParadigmHandler handler = new ParadigmHandler(dao);

            MainCrawler crawler = new MainCrawler();
            crawler.saveWebContentToFile(url);
//            crawler.readFileXML();
            sax.parse(new InputSource(new StringReader(crawler.readFileXML())), handler);
        } catch (SAXParseException e) {
            ErrorUtil.saveLogErrorSAXEntity(e.getMessage().split("\"")[1]);
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readContent(String urlString) throws MalformedURLException, IOException {
        String content = "";

        try {
            URL url = new URL(urlString);
            URLConnection yc = url.openConnection();
            yc.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            InputStream is = yc.getInputStream();
            BufferedReader bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String inputLine;
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("xml.txt"), "UTF-8"));
            boolean isFound = false;
            while ((inputLine = bf.readLine()) != null) {
                if (inputLine.contains("<ul class=\"products")) {
                    isFound = true;
                }
                if (isFound) {
                    String temp = inputLine.trim();
                    temp = temp.replaceAll("</", "\n</")
                            .replaceAll(">", ">\n");
                    content += temp;
                    writer.write(temp);
                }
                if (isFound && inputLine.equals("</ul>")) {
                    break;
                }
            }
            writer.close();
            bf.close();
            is.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return content;
    }

    private void saveWebContentToFile(String urlString) {
        try {
            URL url = new URL(urlString);
            URLConnection yc = url.openConnection();
            yc.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            InputStream is = yc.getInputStream();
            BufferedReader bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String inputLine;
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("xml.txt"), "UTF-8"));
            boolean isFound = false;
            while ((inputLine = bf.readLine()) != null) {
                if (inputLine.contains("<ul class=\"products")) {
                    isFound = true;
                }
                if (isFound) {
                    String temp = inputLine.trim();
                    temp = temp.replaceAll("</", "\n</")
                            .replaceAll(">", ">\n");
                    temp = clearOldErrorEntity(temp);
                    writer.write(temp);
                }
                if (isFound && inputLine.equals("</ul>")) {
                    break;
                }
            }
            writer.close();
            bf.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String clearOldErrorEntity(String str) {
        List<String> oldError = readLogErrorSAXEntity();
        for (String err : oldError) {
            str = str.replaceAll(err, "");
        }
        return str;
    }

    private String readFileXML() {
        String content = "";
        try {
            Stack<String> st = new Stack();
            File f = new File("xml.txt");
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;

            int indexTag = 0;
            while ((line = br.readLine()) != null) {
                String tag = getTag(line);
                if (tag != null) {
                    if (tag.equals("any")) {
                    } else if (tag.contains("</")) {
                        tag = tag.replace("</", "<");
                        if (st.lastElement().equals(tag)) {
                            indexTag = st.size();
                            line = line.replace("</", "</k" + indexTag);
                            st.pop();
                        }
                    } else {
                        st.push(tag);
                        indexTag = st.size();
                        line = line.replace("<", "<k" + indexTag);
                    }
                }

                content += line.trim() + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    private String getTag(String str) {
        if (str.contains("/>")) {
            return "any";
        }

        int beginIndex = str.indexOf("<");
        int endIndex = str.indexOf(" ");
        if (beginIndex >= 0 && endIndex >= 0) {
            return str.substring(beginIndex, endIndex);
        }
        if (beginIndex < 0) {
            return null;
        }
        if (endIndex < 0) {
            return str.substring(beginIndex, str.length() - 1);
        }
        return str;
    }
}
