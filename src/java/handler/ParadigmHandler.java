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
public class ParadigmHandler extends DefaultHandler {

    private String curTag;
    private ParadigmDAO dao;
    private ParadigmDTO dto;
    private boolean hadLink;
    String sp = "";

    public ParadigmHandler(ParadigmDAO dao) throws ClassNotFoundException, SQLException {
        this.dao = dao;
        dao.truncate();
        hadLink = false;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println(sp + "</" + qName + ">");

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println(sp + "<" + qName + ">");
        int len = attributes.getLength();
        for (int i = 0; i < len; i++) {
            System.out.println("-------------" + attributes.getQName(i) +": " +attributes.getValue(i));
        }
//        sp += "   ";
//        if (qName.equals("k10_img")) {
//            System.out.println(attributes.getValue("src"));
//        }
//        switch (qName) {
//            case "k2li":
//                hadLink = false;
//                break;
//            case "k4a":
//                if (!hadLink) {
//                    insertLink(attributes.getValue("href"));
//                }
//                break;
//        }
    }

    public void insertLink(String href) {
        dto = new ParadigmDTO();
        dto.setLink(href);
        dao.createParadigm(dto);
        hadLink = true;
    }
}
