/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.webapp.api;

import pl.gauee.wishlist.utils.decorators.DWishUser;

/**
 *
 * @author gauee
 */
public interface UserApi {

    public DWishUser getUser(String login);

    public DWishUser getDefaultUser();
}
