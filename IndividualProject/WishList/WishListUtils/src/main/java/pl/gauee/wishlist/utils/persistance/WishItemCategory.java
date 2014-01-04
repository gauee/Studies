/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.utils.persistance;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author gauee
 */
@Entity
@Table(name = "WishItemCategory")
public class WishItemCategory implements WishObject {

    private static final long serialVersionUID = 5255779887965755974L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wic_id")
    private long id;
    @Column(name = "wic_name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "wi_wic_id")
    private Set<WishItem> itemInCategory;

    public WishItemCategory() {
    }

    public WishItemCategory(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "WishItemCategory{" + "id=" + id + ", name=" + name + '}';
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<WishItem> getItemInCategory() {
        return itemInCategory;
    }

    public void setItemInCategory(Set<WishItem> itemInCategory) {
        this.itemInCategory = itemInCategory;
    }
}
