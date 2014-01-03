/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.utils;

import java.io.Serializable;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;
import pl.gauee.wishlist.core.persistance.WishItem;
import pl.gauee.wishlist.core.persistance.WishItemCategory;
import pl.gauee.wishlist.core.persistance.WishItemInList;
import pl.gauee.wishlist.core.persistance.WishList;
import pl.gauee.wishlist.core.persistance.WishUser;
import pl.gauee.wishlist.core.persistance.WishUserGroup;

/**
 *
 * @author gauee
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new AnnotationConfiguration()
                    .configure()
                    .addAnnotatedClass(WishItem.class)
                    .addAnnotatedClass(WishItemCategory.class)
                    .addAnnotatedClass(WishItemInList.class)
                    .addAnnotatedClass(WishList.class)
                    .addAnnotatedClass(WishUser.class)
                    .addAnnotatedClass(WishUserGroup.class)
                    .buildSessionFactory();

        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getNewSession() {
        return sessionFactory.openSession();
    }

    public static Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
