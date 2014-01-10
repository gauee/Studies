/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.utils.persistance.rest;

import pl.gauee.wishlist.utils.persistance.WishItem;

/**
 *
 * @author gauee
 */
public class RestWishItem extends RestObject<RestWishItem, WishItem> {

    private static final long serialVersionUID = 1978998391030475180L;
//    private long id;
    private String name;
    private String describtion;
    private String photoUrl;
    private double price;
    private boolean bought;

    public RestWishItem() {
    }

    @Override
    public String toString() {
        return "RestWishItem{" + "name=" + name + ", describtion=" + describtion + ", photoUrl=" + photoUrl + ", price=" + price + ", bought=" + bought + '}';
    }

    @Override
    public RestWishItem packTo(WishItem sourceObject) {
        RestWishItem restItem = new RestWishItem();
        if (null == sourceObject) {
            return restItem;
        }

        restItem.setId(sourceObject.getId());
        restItem.setName(sourceObject.getName());
        restItem.setDescription(sourceObject.getDescription());
        restItem.setPhotoUrl(sourceObject.getPhotoUrl());
        restItem.setPrice(sourceObject.getPrice());
        restItem.setBought(sourceObject.isBought());

        return restItem;

    }

    @Override
    public WishItem repackFrom(RestWishItem sourceObject) {
        WishItem restItem = new WishItem();
        if (null == sourceObject) {
            return restItem;
        }

        restItem.setId(sourceObject.getId());
        restItem.setName(sourceObject.getName());
        restItem.setDescription(sourceObject.getDescription());
        restItem.setPhotoUrl(sourceObject.getPhotoUrl());
        restItem.setPrice(sourceObject.getPrice());
        restItem.setBought(sourceObject.isBought());

        return restItem;

    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return describtion;
    }

    public void setDescription(String describe) {
        this.describtion = describe;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
