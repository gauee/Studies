/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.utils.persistance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author gauee
 */
@Entity
@Table(name = "WishItem")
public class WishItem implements WishObject {

    private static final long serialVersionUID = 8277298816098447876L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wi_id")
    private long id;
    @Column(name = "wi_name")
    private String name;
    @Column(name = "wi_description")
    private String description;
    @Column(name = "wi_price", columnDefinition = "Decimal(10,2) default '100.00'")
    private double price;
    @Column(name = "wi_photo")
    private String photoUrl;
    @ManyToOne
    @JoinColumn(name = "wi_wic_id")
    private WishItemCategory category;

    public WishItem() {
    }

    public WishItem(String name, String description, double price, String photoUrl, WishItemCategory category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.photoUrl = photoUrl;
        this.category = category;
    }
    
    

    @Override
    public String toString() {
        return "WishItem{" + "id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", photoUrl=" + photoUrl + '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public WishItemCategory getCategory() {
        return category;
    }

    public void setCategory(WishItemCategory category) {
        this.category = category;
    }
}
