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
public class ParadigmDTO implements Serializable {

    private String name, size, brand, hard, price, link, image;
    private int id;

    public ParadigmDTO() {
         this.name = "";
        this.size = "";
        this.brand = "";
        this.hard = "";
        this.price = "";
        this.link = "";
        this.image = "";
    }

    public ParadigmDTO(String name, String size, String brand, String hard, String price, String link, String image, int id) {
        this.name = name;
        this.size = size;
        this.brand = brand;
        this.hard = hard;
        this.price = price;
        this.link = link;
        this.image = image;
        this.id = id;
    }

   

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @XmlElement
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @XmlElement
    public String getHard() {
        return hard;
    }

    public void setHard(String hard) {
        this.hard = hard;
    }

    @XmlElement
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @XmlElement
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @XmlElement
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
