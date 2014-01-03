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
@Table(name = "WishUserGroup")
public class WishUserGroup implements WishObject {

    private static final long serialVersionUID = -2639931312043809982L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wug_id")
    private long id;
    @Column(name = "wug_name")
    private String name;
    @Column(name = "wug_created_time")
    private Date creastedTime;

    public WishUserGroup() {
    }

    @Override
    public String toString() {
        return "WishUserGroup{" + "id=" + id + ", name=" + name + ", creastedTime=" + creastedTime + '}';
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

    public Date getCreastedTime() {
        return creastedTime;
    }

    public void setCreastedTime(Date creastedTime) {
        this.creastedTime = creastedTime;
    }
}
