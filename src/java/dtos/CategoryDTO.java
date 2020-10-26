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
public class CategoryDTO implements Serializable {

    private String id, category, link;

    public CategoryDTO() {
        this.id = "";
        this.category = "";
        this.link = "";
    }

    public CategoryDTO(String id, String category, String link) {
        this.id = id;
        this.category = category;
        this.link = link;
    }

    @XmlElement
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @XmlElement
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
