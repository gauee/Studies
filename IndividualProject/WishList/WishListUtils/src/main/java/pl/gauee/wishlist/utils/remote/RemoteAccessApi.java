/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.utils.remote;

import pl.gauee.wishlist.utils.persistance.WishUser;

/**
 *
 * @author gauee
 */
public interface RemoteAccessApi {

    public long testBySquare(int x);

    public boolean authenticateUser(String userLogin, String userPassHash);

    public boolean isUserNameExists(String userName);

    public WishUser createNewWishUser(WishUser user);

    public WishUser getUserByLogin(String userName);
}
