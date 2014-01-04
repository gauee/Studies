/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.webapp.api;

import java.util.List;
import pl.gauee.wishlist.utils.persistance.WishList;

/**
 *
 * @author gauee
 */
public interface ListApi {

    public WishList getList(Long listId);

    public List<WishList> getListsOwnedToUser(Long userId);

    public List<WishList> getDefaultWishList();
}
