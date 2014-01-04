/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.webapp.factories;

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
    public boolean isTwoPassIdentical(String pass1, String pass2) {
        if (null == pass1) {
            return false;
        }
        return pass1.equals(pass2);
    }
}
