/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.webapp.factories;

import pl.gauee.wishlist.utils.decorators.DWishUser;
import pl.gauee.wishlist.webapp.api.UserApi;

/**
 *
 * @author gauee
 */
class UserAccess implements UserApi {

    @Override
    public DWishUser getUser(String login) {
        return new DWishUser();
    }

    @Override
    public DWishUser getDefaultUser() {
        return new DWishUser("gauee", "Damian", "Gałka", "galka.damian.91@gmail.com", "503109746");
    }
}
