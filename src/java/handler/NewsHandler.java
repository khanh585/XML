/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;

import daos.NewsDAO;
import dtos.NewsDTO;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Khanh
 */
public class NewsHandler extends DefaultHandler {

    private String curTag;
    private NewsDAO dao;
    private NewsDTO dto;
    private boolean foundItem;

    public NewsHandler() {
    }

    public NewsHandler(NewsDAO dao) {
        this.dao = dao;
        foundItem = false;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String str = new String(ch, start, length);
        if (foundItem) {
            if (curTag.equals("title")) {
                dto.setTitle(str.trim());
            } else if (curTag.equals("description")) {
                dto.setDesc(str.trim());
            } else if (curTag.equals("link")) {
                dto.setLink(str.trim());
            } else if (curTag.equals("pubDate")) {
                dto.setPubDate(str.trim());
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("item")) {
            if (dto != null) {
                dao.createNews(dto);
            }
            foundItem = false;
        }
        curTag = "";
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("item")) {
            dto = new NewsDTO();
            foundItem = true;
        }
        curTag = qName;
    }

}
