/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.dao;

import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import pl.gauee.wishlist.core.api.UserApi;
import pl.gauee.wishlist.core.persistance.WishUser;
import pl.gauee.wishlist.core.utils.HibernateUtil;

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
        Session session = HibernateUtil.getNewSession();
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
}
