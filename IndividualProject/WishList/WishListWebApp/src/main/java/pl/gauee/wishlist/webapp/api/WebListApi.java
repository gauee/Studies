/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.webapp.api;

import java.util.List;
import pl.gauee.wishlist.utils.api.ListApi;
import pl.gauee.wishlist.utils.persistance.WishList;
import pl.gauee.wishlist.utils.persistance.WishUser;

/**
 *
 * @author gauee
 */
public interface WebListApi extends ListApi {

    public List<WishList> getListsOwnedToUser(String userName);

    public boolean createNewListForUser(WishList list, String userName);

    public List<WishList> getDefaultWishList();
}
