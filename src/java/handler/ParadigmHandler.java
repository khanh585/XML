/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;

import daos.ParadigmDAO;
import dtos.ParadigmDTO;
import java.sql.SQLException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Khanh
 */
public class ParadigmHandler extends DefaultHandler{
    private String curTag;
    private ParadigmDAO dao;
    private ParadigmDTO dto;
    private boolean isStart;

    public ParadigmHandler(ParadigmDAO dao) throws ClassNotFoundException, SQLException {
        this.dao = dao;
        isStart = false;
    }
    
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
//System.out.println("</"+qName+'>');
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//        System.out.println('<'+qName+'>');
//        if (qName.equals("li")) {
//            isStart = true;
//        }
//        if(qName.equals("a") && isStart) {
//            String link = attributes.getValue("href");
//            dto = new ParadigmDTO();
//            dto.setLink(link);
//            dao.createParadigm(dto);
//            isStart = false;
//        }
//        curTag = qName;
    }
}
