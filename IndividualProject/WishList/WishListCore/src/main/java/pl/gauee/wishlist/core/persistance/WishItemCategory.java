/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.persistance;

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
@Table(name = "WishItemCategory")
public class WishItemCategory implements WishObject {

    private static final long serialVersionUID = 5255779887965755974L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wic_id")
    private long id;
    @Column(name = "wic_name")
    private String name;

    public WishItemCategory() {
    }

    public WishItemCategory(long id, String name) {
        this.id = id;
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
}
