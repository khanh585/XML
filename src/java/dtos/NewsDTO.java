/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Khanh
 */
@XmlRootElement
public class NewsDTO implements Serializable {

    private String title, desc, link, pubDate;
    private int id;

    public NewsDTO() {
    }

    public NewsDTO(String title, String desc, String link, String pubDate, int id) {
        this.title = title;
        this.desc = desc;
        this.link = link;
        this.pubDate = pubDate;
        this.id = id;
    }

    @XmlElement
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @XmlElement
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @XmlElement
    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    @XmlElement
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
