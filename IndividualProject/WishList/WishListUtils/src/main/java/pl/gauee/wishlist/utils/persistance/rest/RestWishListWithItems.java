/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.utils.persistance.rest;

import java.util.List;

/**
 *
 * @author gauee
 */
public class RestWishListWithItems {

    private RestWishList list;
    private List<RestWishItem> items;

    public RestWishListWithItems() {
    }

    public RestWishListWithItems(RestWishList list, List<RestWishItem> items) {
        this.list = list;
        this.items = items;
    }

    public RestWishList getList() {
        return list;
    }

    public void setList(RestWishList list) {
        this.list = list;
    }

    public List<RestWishItem> getItems() {
        return items;
    }

    public void setItems(List<RestWishItem> items) {
        this.items = items;
    }
}
