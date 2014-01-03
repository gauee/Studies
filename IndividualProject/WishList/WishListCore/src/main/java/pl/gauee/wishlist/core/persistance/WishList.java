/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.persistance;

import java.sql.Date;
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
@Table(name = "WishList")
public class WishList implements WishObject {

    private static final long serialVersionUID = -8979136921462582767L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wl_id")
    private long id;
    @Column(name = "wl_created_time")
    private Date createdDate;
    @Column(name = "wl_name")
    private String name;

    public WishList() {
    }

    public WishList(long id, Date createdDate, String name) {
        this.id = id;
        this.createdDate = createdDate;
        this.name = name;
    }

    @Override
    public String toString() {
        return "WishList{" + "id=" + id + ", createdDate=" + createdDate + ", name=" + name + '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
