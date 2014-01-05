/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.utils.api;

import java.util.List;
import pl.gauee.wishlist.utils.persistance.WishUser;

/**
 *
 * @author gauee
 */
public interface UserApi {

    public WishUser createUser(WishUser user);

    public WishUser getUserByLogin(String login);

    public boolean isUserExist(String login);

    public boolean authenticateUserWithPassHash(String userName, String passHash);

    public boolean updateUser(WishUser user);

    public boolean joinTwoUserAsFriends(WishUser user1, WishUser user2);

    public List<WishUser> getAllUsers();

}
