/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.utils.api;

import pl.gauee.wishlist.utils.persistance.WishList;

/**
 *
 * @author gauee
 */
public interface ListApi {
    
    public WishList getListById(Long listId);
}
