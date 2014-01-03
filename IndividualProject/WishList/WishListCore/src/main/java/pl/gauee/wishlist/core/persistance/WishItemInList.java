/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.persistance;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import pl.gauee.wishlist.core.api.WishObject;

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

    public WishItemInList() {
    }

    public WishItemInList(long id, boolean bought, Date lastUpdate) {
        this.id = id;
        this.bought = bought;
        this.lastUpdate = lastUpdate;
    }

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
}
