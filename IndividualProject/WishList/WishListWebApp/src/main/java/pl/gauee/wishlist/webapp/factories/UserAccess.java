/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.webapp.factories;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.gauee.wishlist.utils.persistance.WishUser;
import pl.gauee.wishlist.utils.remote.RemoteAccessApi;
import pl.gauee.wishlist.webapp.api.WebUserApi;

/**
 *
 * @author gauee
 */
@Component
class UserAccess implements WebUserApi {

    @Autowired
    @Qualifier(value = "proxyBean")
    RemoteAccessApi remoteAccessApi;
    private final Logger logger = Logger.getLogger(UserAccess.class);

    @Override
    public WishUser getDefaultUser() {
        WishUser user = new WishUser();
        user.setLogin("gauee");
        user.setPassHash("pass_hash");
        user.setName("DamianTest");
        user.setSurname("GałkaTest");
        user.setEmail("mail@testowy.pl");
        user.setMsisdn("123123123");
        return user;
    }

    @Override
    public WishUser createUser(WishUser userToCreate) {
        return remoteAccessApi.createNewWishUser(userToCreate);
    }

    @Override
    public WishUser getUserByLogin(String login) {
        return remoteAccessApi.getUserByLogin(login);
    }

    @Override
    public boolean isUserExist(String login) {
        return remoteAccessApi.isUserNameExists(login);
    }

    @Override
    public boolean authenticateUserWithPassHash(String userName, String passHash) {
        return remoteAccessApi.authenticateUser(userName, passHash);
    }

    @Override
    public boolean updateUser(WishUser user) {
        return remoteAccessApi.updateUser(user);
    }

    @Override
    public boolean joinTwoUserAsFriends(WishUser user1, WishUser user2) {
        return remoteAccessApi.joinTwoUsersAsFriends(user1, user2);
    }

    @Override
    public List<WishUser> getAllUsers() {
        return remoteAccessApi.getAllUsers();
    }

    @Override
    public List<String> getNonFriendsLoginForUser(String userName) {
        List<String> nonFriends = new ArrayList<String>();
        WishUser loggedUser = remoteAccessApi.getUserByLogin(userName);
        List<WishUser> allUsers = remoteAccessApi.getAllUsers();
        logger.info("Total user is: " + allUsers.size());

        allUsers.remove(loggedUser);
        for (WishUser friend : loggedUser.getUserFriends()) {
            allUsers.remove(friend);
        }

        for (WishUser nonFriend : allUsers) {
            nonFriends.add("'" + nonFriend.getLogin() + "'");
        }

        return nonFriends;
    }

    @Override
    public void deleteFriendship(WishUser user, WishUser userToDelete) {
        remoteAccessApi.deleteFriendship(user, userToDelete);
    }

    @Override
    public boolean isTwoPassIdentical(String pass1, String pass2) {
        if (null == pass1) {
            return false;
        }
        return pass1.equals(pass2);
    }
}
