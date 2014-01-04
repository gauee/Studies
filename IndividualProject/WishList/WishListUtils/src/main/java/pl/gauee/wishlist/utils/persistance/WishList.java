/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.utils.persistance;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
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
    
//    private List<WishItemInList> items;

    public WishList() {
    }

    public WishList(String name) {
        this.createdDate = new Date(System.currentTimeMillis());
        this.name = name;
    }

//    public WishList(String name, List<WishItemInList> items) {
//        this.createdDate = new Date(System.currentTimeMillis());
//        this.name = name;
//        this.items = items;
//    }

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

    public List<WishItemInList> getItems() {
        return new LinkedList<WishItemInList>();
    }
}
