/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.utils.api;

import pl.gauee.wishlist.utils.persistance.WishItem;

/**
 *
 * @author gauee
 */
public interface ItemApi {

    public WishItem createItem(WishItem item);
}
