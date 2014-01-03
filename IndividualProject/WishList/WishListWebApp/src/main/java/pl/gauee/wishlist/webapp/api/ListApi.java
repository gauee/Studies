/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.webapp.api;

import java.util.List;
import pl.gauee.wishlist.utils.decorators.DWishList;

/**
 *
 * @author gauee
 */
public interface ListApi {

    public DWishList getList(Long listId);

    public List<DWishList> getListsOwnedToUser(Long userId);

    public List<DWishList> getDefaultWishList();
}
