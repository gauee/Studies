/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.utils;

import java.io.Serializable;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;
import pl.gauee.wishlist.core.persistance.WishItem;
import pl.gauee.wishlist.core.persistance.WishUser;

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
                    .addAnnotatedClass(WishUser.class)
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

    public static Serializable saveObject(Object o) {
        Session session = getNewSession();
        Serializable id = session.save(o);
        session.close();

        return id;
    }

    public static void saveOrUpdateObject(Object o) {
        Session session = getNewSession();
        session.saveOrUpdate(o);
        session.close();
    }

    public static <T> T getObjectById(Class<T> classType, long id) {
        Session session = getNewSession();
        Object o = session.get(classType, id);
        session.close();
        return classType.cast(o);
    }

    public static void deleteObject(Object o) {
        Session session = getNewSession();
        session.delete(o);
    }
}
