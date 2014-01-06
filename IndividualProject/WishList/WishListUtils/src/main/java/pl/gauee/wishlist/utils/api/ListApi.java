/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.utils.api;

import pl.gauee.wishlist.utils.persistance.WishItem;
import pl.gauee.wishlist.utils.persistance.WishList;

/**
 *
 * @author gauee
 */
public interface ListApi {

    public WishList getListById(Long listId);

    public WishList createList(WishList list);

    public void deleteList(long listId);

    public void addItemToList(WishItem wishItem, long listId);

    public void updateList(WishList wishList);
}
