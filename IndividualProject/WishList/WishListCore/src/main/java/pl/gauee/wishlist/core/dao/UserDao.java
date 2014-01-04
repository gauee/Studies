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
public class UserDao extends BaseDao<WishUser> implements UserApi {

    public static final Class<WishUser> classType = WishUser.class;

    @Override
    public Class getClassType() {
        return classType;
    }

    public boolean isUserExist(String login) {
        return false;
    }
}
