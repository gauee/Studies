/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.dao;

import pl.gauee.wishlist.core.api.UserApi;
import pl.gauee.wishlist.core.persistance.WishUser;

/**
 *
 * @author gauee
 */
public class UserDao extends BaseDao implements UserApi {

    private final Class classType = WishUser.class;

    @Override
    public Class getClassType() {
        return classType;
    }

    public boolean isUserExist(String login) {
        return false;
    }
}
