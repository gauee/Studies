/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.utils.persistance.rest;

import pl.gauee.wishlist.utils.persistance.WishList;

/**
 *
 * @author gauee
 */
public class RestWishList extends RestObject<RestWishList, WishList> {

    private static final long serialVersionUID = -9160002545341415200L;
    private String name;

    public RestWishList() {
    }

    @Override
    public RestWishList packTo(WishList sourceObject) {
        RestWishList resultObject = new RestWishList();

        if (null == sourceObject) {
            return resultObject;
        }

        resultObject.setId(sourceObject.getId());
        resultObject.setName(sourceObject.getName());

        return resultObject;

    }

    @Override
    public WishList repackFrom(RestWishList sourceObject) {

        WishList resultObject = new WishList();

        if (null == sourceObject) {
            return resultObject;
        }

        resultObject.setId(sourceObject.getId());
        resultObject.setName(sourceObject.getName());

        return resultObject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
