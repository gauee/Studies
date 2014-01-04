/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.api;

import pl.gauee.wishlist.core.persistance.WishUser;

/**
 *
 * @author gauee
 */
public interface UserApi {

    public WishUser getUserByLogin(String login);

    public boolean isUserExist(String login);

    public boolean authenticateUserWithPassHash(String userName, String passHash);
}
