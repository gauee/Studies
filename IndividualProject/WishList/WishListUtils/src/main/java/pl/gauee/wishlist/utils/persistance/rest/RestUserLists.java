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
public class RestUserLists {

    private List<RestWishList> lists;

    public RestUserLists() {
    }

    public RestUserLists(List<RestWishList> lists) {
        this.lists = lists;
    }

    public List<RestWishList> getLists() {
        return lists;
    }

    public void setLists(List<RestWishList> lists) {
        this.lists = lists;
    }
}
