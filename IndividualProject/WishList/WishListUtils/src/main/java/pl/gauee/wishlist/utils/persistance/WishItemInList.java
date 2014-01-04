/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.utils.persistance;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author gauee
 */
@Entity
@Table(name = "WishItemInList")
public class WishItemInList implements WishObject {

    private static final long serialVersionUID = 7809641968663834551L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wiil_id")
    private long id;
    @Column(name = "wiil_bought")
    private boolean bought;
    @Column(name = "wiil_last_update")
    private Date lastUpdate;
//    private WishItem item;

    public WishItemInList() {
    }

    public WishItemInList(boolean bought) {
        this.bought = bought;
        this.lastUpdate = lastUpdate;
//        this.item = item;
    }

//    public WishItemInList(String name, String description, double price, String photoUrl) {
//        this.item = new WishItem(name, description, price, photoUrl, new WishItemCategory("unknown"));
//        this.bought = false;
//        this.lastUpdate = new Date(System.currentTimeMillis());
//    }

    @Override
    public String toString() {
        return "WishItemInList{" + "id=" + id + ", bought=" + bought + ", lastUpdate=" + lastUpdate + '}';
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public WishItem getItem() {
        return new WishItem("item1", "opis1", 10.12, "src1", new WishItemCategory("unknown"));
    }

}
