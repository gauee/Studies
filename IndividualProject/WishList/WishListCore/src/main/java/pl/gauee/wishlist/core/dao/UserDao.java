/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.dao;

import java.util.List;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import pl.gauee.wishlist.utils.api.UserApi;
import pl.gauee.wishlist.utils.persistance.WishList;
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

    @Override
    public List<WishUser> getAllUsers() {
        return super.getAll();
    }

    public boolean addListToUser(WishList list, String userName) {
        WishUser user = getUserByLogin(userName);
        user.getUserLists().add(list);
        super.update(user);
        return true;
    }

    public boolean joinTwoUserAsFriends(WishUser user1, WishUser user2) {
        if (user1 == null || user2 == null) {
            return false;
        }
        user1.getUserFriends().add(user2);
        user2.getUserFriends().add(user1);
        super.update(user1);
        super.update(user2);
        return true;
    }
}
