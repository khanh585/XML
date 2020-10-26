/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.crawler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Stack;
import static utils.ErrorUtil.readLogErrorSAXEntity;

/**
 *
 * @author Khanh
 */
public class UtilsCrawler {

    private static final String XMLFile = "E:\\Study\\webservice_JDBC_XHR\\web\\WEB-INF\\";
    private String nameFile;
    private String urlString;

    private String startPoint;
//    private String endPoint;

    public UtilsCrawler(String nameFile, String urlString, String startPoint) {
        this.nameFile = XMLFile + nameFile;
        this.urlString = urlString;
        this.startPoint = startPoint;
//        this.endPoint = endPoint;
    }

    public void saveWebContentToFile() {
        try {
            File f = new File(nameFile);
            URL url = new URL(urlString);
            URLConnection yc = url.openConnection();
            yc.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            InputStream is = yc.getInputStream();
            BufferedReader bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String inputLine;
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(nameFile), "UTF-8"));
            boolean isFound = false;
            while ((inputLine = bf.readLine()) != null) {
                if (inputLine.contains("<body")) {
                    isFound = true;
                }
                if (isFound) {
                    String temp = inputLine.trim();
                    temp = temp.replaceAll("<", "\n<");

                    temp = clearOldErrorEntity(temp);
                    writer.write(temp);
                }
                if (isFound && inputLine.equals("</body>")) {
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

    public String readFileXML() {
        String content = "";
        try {
            Stack<String> st = new Stack();
            File f = new File(nameFile);
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            int indexTag = 0;
            boolean isFound = false;
            while ((line = br.readLine()) != null) {
                if (line.contains(startPoint)) {
                    isFound = true;
                }
                if (isFound) {
                    line = fixValidHTMLTag(line);
                    String tag = getTag(line);
                    if(tag.equals("any")){
                        content += line;
                        continue;
                    }
                    if (line.contains("</")) {
                        line = line.replace("</", "</k"+st.size());
                        st.pop();
                    } else if (line.contains("<")) {
                        st.push(tag);
                        line = line.replace("<", "<k"+st.size());
                    }
                    System.out.println("-  "+ line);
                    content += line;
                }
                if (isFound && st.size() == 0) {
                    break;
                }
            }
            System.out.println("---------END Utils Crawl---------");
        } catch (Exception e) {
            System.out.println("UtilsCrawler.readFileXML");
            e.printStackTrace();
        }
        return content;
    }

    private String fixValidHTMLTag(String line) {
        if (line.contains("<img")) {
            if (!line.contains("/>")) {
                line = line.replaceAll(">", "/>");
            }
        }
        return line;
    }

    private String clearOldErrorEntity(String str) {
        List<String> oldError = readLogErrorSAXEntity();
        for (String err : oldError) {
            str = str.replaceAll(err, "");
        }
        return str;
    }

    private String getTag(String str) {
        if (str.contains("/>")) {
            return "any";
        }
        try {
            String reg = "(<|>)";
            str = str.replace(reg, " ").trim();
            str = str.split(reg)[1].split(" ")[0];
        } catch (Exception e) {
            return "";
        }
        return str;
    }
}
