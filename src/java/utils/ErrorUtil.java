/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.StringReader;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Khanh
 */
public class ErrorUtil {

    private static final String ERROR_ENTITY_File = "E:\\Study\\webservice_JDBC_XHR\\web\\WEB-INF\\error.txt";

    public static void saveLogErrorSAXEntity(String newErrorEntity) {
        newErrorEntity = "&" + newErrorEntity;
        List<String> listError = readLogErrorSAXEntity();
        try {
            if (!listError.contains(newErrorEntity)) {
                Writer w = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(ERROR_ENTITY_File)));
                listError.add(newErrorEntity);
                for (String err : listError) {
                    w.write(err + "\n");
                }
                w.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> readLogErrorSAXEntity() {
        List<String> result = new ArrayList<>();
        try {
            File f = new File(ERROR_ENTITY_File);
            f.createNewFile();
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                result.add(line.trim());
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
