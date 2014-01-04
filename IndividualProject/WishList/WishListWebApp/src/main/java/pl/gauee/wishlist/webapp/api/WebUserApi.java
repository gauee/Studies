/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.webapp.api;

import pl.gauee.wishlist.utils.api.UserApi;
import pl.gauee.wishlist.utils.persistance.WishUser;

/**
 *
 * @author gauee
 */
public interface WebUserApi extends UserApi {

    public WishUser getDefaultUser();

    public boolean isTwoPassIdentical(String pass1, String pass2);
}
