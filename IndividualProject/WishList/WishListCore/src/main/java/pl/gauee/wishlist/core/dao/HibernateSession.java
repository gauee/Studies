/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.dao;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import pl.gauee.wishlist.utils.persistance.HibernateUtil;

/**
 *
 * @author gauee
 */
public class HibernateSession extends HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

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
