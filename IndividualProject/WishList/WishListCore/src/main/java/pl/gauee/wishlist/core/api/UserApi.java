/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.api;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import pl.gauee.wishlist.core.persistance.WishUser;
import pl.gauee.wishlist.core.utils.HibernateUtil;

/**
 *
 * @author gauee
 */
public class UserApi {

//    CRUD
    public static WishUser createUser(String login, String pass, String name, String surname) {
        WishUser user = new WishUser();
        user.setLogin(login);
        user.setPassHash("Hash:" + pass.hashCode());
        user.setName(name);
        user.setSurname(surname);

        Long id = (Long) HibernateUtil.saveObject(user);
        user.setId(id);

        return user;
    }

    public static WishUser getUser(String login) {
        Session session = HibernateUtil.getNewSession();
        WishUser user = (WishUser) session.createCriteria(WishUser.class).
                add(Restrictions.eq("login", login)).uniqueResult();
        session.close();
        return user;
    }

    public static WishUser getUser(long id) {
        WishUser user = HibernateUtil.getObjectById(WishUser.class, id);
        return user;
    }

    public static boolean updateUser(WishUser toUpdateUser) {
        HibernateUtil.saveOrUpdateObject(toUpdateUser);
        return true;
    }

    public static boolean deleteUser(long id) {
        HibernateUtil.deleteObject(id);

        return true;
    }

    public static boolean deleteUser(String login) {
        WishUser user = getUser(login);
        HibernateUtil.deleteObject(user.getId());

        return true;
    }

    public static boolean isUserExist(long id) {
        WishUser user = HibernateUtil.getObjectById(WishUser.class, id);

        return user == null ? false : true;
    }

    public static boolean isUserExist(String login) {
        WishUser user = getUser(login);

        return user == null ? false : true;
    }
}
