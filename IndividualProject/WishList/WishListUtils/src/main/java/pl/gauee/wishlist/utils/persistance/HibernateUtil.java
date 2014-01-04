/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.utils.persistance;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author gauee
 */
public abstract class HibernateUtil {

    protected static SessionFactory buildSessionFactory() {
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
}
