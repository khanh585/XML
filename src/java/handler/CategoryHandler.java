/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;

import daos.CategoryDAO;
import dtos.CategoryDTO;
import java.sql.SQLException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Khanh
 */
public class CategoryHandler extends DefaultHandler{
     private String curTag;
    private CategoryDAO dao;
    private CategoryDTO dto;
    private boolean hadLink;

    public CategoryHandler(CategoryDAO dao) throws ClassNotFoundException, SQLException {
        this.dao = dao;
        dao.truncate();
        hadLink = false;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("</"+qName+">");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("<"+qName+">");
        int len = attributes.getLength();
        for(int i = 0; i < len; i++){
            System.out.println( "-------------"+attributes.getValue(i));
        }
    }

    public void insertLink(String href) {
        dto = new CategoryDTO();
        dto.setLink(href);
        dao.createCategory(dto);
        hadLink = true;
    }
}
