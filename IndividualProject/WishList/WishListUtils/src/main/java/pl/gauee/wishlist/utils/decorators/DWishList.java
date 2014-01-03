/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.utils.decorators;

import java.util.Date;
import java.util.List;

/**
 *
 * @author gauee
 */
public class DWishList {

    private String name;
    private Date createdTime;
    private List<DWishItemInList> items;

    public DWishList() {
    }

    public DWishList(String name, List<DWishItemInList> items) {
        this.name = name;
        this.createdTime = new Date();
        this.items = items;
    }

    @Override
    public String toString() {
        return "DWishList{" + "name=" + name + ", createdTime=" + createdTime + ", items=" + items + '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public List<DWishItemInList> getItems() {
        return items;
    }

    public void setItems(List<DWishItemInList> items) {
        this.items = items;
    }
}
