/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.api;

import java.io.Serializable;
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

        Session session = HibernateUtil.getNewSession();
        Transaction tx = session.beginTransaction();
        Long id = (Long) session.save(user);
        tx.commit();
        session.close();

        user.setId(id);

        return user;
    }

    public static WishUser getUser(String login) {
        return null;
    }

    public static WishUser getUser(long id) {
        return null;
    }

    public static boolean updateUser(WishUser toUpdateUser) {
        return true;
    }

    public static boolean deleteUser(long id) {
        return true;
    }

    public static boolean deleteUser(String login) {
        return true;
    }

    public static boolean isUserExist(long id) {
        Session session = HibernateUtil.getNewSession();

        WishUser user = (WishUser) session.get(WishUser.class, id);

        session.close();

        return user == null ? false : true;
    }

    public static boolean isUserExist(String login) {
        Session session = HibernateUtil.getNewSession();

        WishUser user = (WishUser) session.createCriteria(WishUser.class).add(
                Restrictions.eq("login", login)).uniqueResult();

        session.close();

        return user == null ? false : true;
    }
}
