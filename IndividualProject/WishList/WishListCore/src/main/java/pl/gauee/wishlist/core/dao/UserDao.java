/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.dao;

import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import pl.gauee.wishlist.utils.api.UserApi;
import pl.gauee.wishlist.utils.persistance.WishUser;

/**
 *
 * @author gauee
 */
class UserDao extends BaseDao<WishUser> implements UserApi {

    public static final Class<WishUser> classType = WishUser.class;

    @Override
    public Class getClassType() {
        return classType;
    }

    public WishUser getUserByLogin(String login) {
        Session session = HibernateSession.getNewSession();
        WishUser user = (WishUser) session.createCriteria(getClassType()).add(Restrictions.eq("login", login)).uniqueResult();
        session.close();
        return user;
    }

    public boolean isUserExist(String login) {
        return getUserByLogin(login) != null;
    }

    public boolean authenticateUserWithPassHash(String userName, String passHash) {
        WishUser user = getUserByLogin(userName);
        return user != null && user.getPassHash().equals(passHash);
    }

    public WishUser createUser(WishUser user) {
        return super.create(user);
    }

    public boolean updateUser(WishUser user) {
        return null != super.update(user);
    }
}
