/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.lists;

import pl.gauee.wishlist.utils.persistance.WishUser;
import pl.gauee.wishlist.utils.persistance.WishItem;
import pl.gauee.wishlist.utils.persistance.WishList;

/**
 *
 * @author gauee
 */
public interface ListOperations {

    public WishList createNewWishList(String listName, WishUser owner, WishList... items);

    public boolean addToWishList(WishList list, WishItem... items);

    public WishList getWishList(int id);
}
