/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.utils.decorators;

import java.util.Date;

/**
 *
 * @author gauee
 */
public class DWishItemInList {

    private String name;
    private String photo;
    private String description;
    private double price;
    private boolean bougth;
    private Date lastUpdate;

    public DWishItemInList() {
    }

    public DWishItemInList(String name, String photo, String description, double price) {
        this.name = name;
        this.photo = photo;
        this.description = description;
        this.price = price;
        this.bougth = false;
        this.lastUpdate = new Date();
    }

    @Override
    public String toString() {
        return "DWishItemInList{" + "name=" + name + ", photo=" + photo + ", description=" + description + ", price=" + price + ", bougth=" + bougth + ", lastUpdate=" + lastUpdate + '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isBougth() {
        return bougth;
    }

    public void setBougth(boolean bougth) {
        this.bougth = bougth;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
