/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.utils.persistance;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
    private Timestamp createdDate;
    @Column(name = "wl_name")
    private String name;
    @ManyToMany(mappedBy = "userLists", fetch = FetchType.EAGER)
    private Set<WishUser> listUsers=  new HashSet<WishUser>();

    public WishList() {
    }

    public WishList(String name) {
        this.createdDate = new Timestamp(System.currentTimeMillis());
        this.name = name;
    }

    @Override
    public String toString() {
        return "WishList{" + "id=" + id + ", createdDate=" + createdDate + ", name=" + name + ", listUsers=" + getUsersName(listUsers) + '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
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

    public Set<WishUser> getListUsers() {
        return listUsers;
    }

    public void setListUsers(Set<WishUser> listUsers) {
        this.listUsers = listUsers;
    }

    private String getUsersName(Set<WishUser> listUsers) {
        StringBuilder sb = new StringBuilder();
        sb.append(",");
        for (WishUser user : listUsers) {
            sb.append(user.getLogin())
                    .append(",");
        }

        return sb.substring(1);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        WishObject toCompare = (WishObject) obj;
        return this.getId() == toCompare.getId();
    }

    @Override
    public int hashCode() {
        return (int) id;
    }
}
