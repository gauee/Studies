/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.classic.Session;
import pl.gauee.wishlist.core.api.DaoApi;
import pl.gauee.wishlist.core.api.WishObject;
import pl.gauee.wishlist.core.utils.HibernateUtil;

/**
 *
 * @author gauee
 */
public abstract class BaseDao implements DaoApi {

    public WishObject create(WishObject objectToSave) {
        Session session = HibernateUtil.getNewSession();
        session.beginTransaction();

        Serializable id = session.save(objectToSave);
        session.getTransaction().commit();

        objectToSave.setId((Long) id);
        return objectToSave;
    }

    public WishObject getById(Long id) {
        Session session = HibernateUtil.getNewSession();
        WishObject objectById = (WishObject) session.get(getClassType(), id);
        return objectById;
    }

    public List<WishObject> getAll() {
        Session session = HibernateUtil.getNewSession();
        List<WishObject> list = session.createCriteria(getClassType()).list();
        return list;
    }

    public WishObject update(WishObject object) {
        Session session = HibernateUtil.getNewSession();
        session.beginTransaction();
        session.update(object);
        session.getTransaction().commit();
        return object;
    }

    public void delete(WishObject objectToDelete) {
        Session session = HibernateUtil.getNewSession();
        session.beginTransaction();
        session.delete(objectToDelete);
        session.flush();
        session.getTransaction().commit();
    }

    public void deleteAll() {
        Session session = HibernateUtil.getNewSession();
        session.beginTransaction();
        List<WishObject> toDeleteList = getAll();
        for (WishObject toDelete : toDeleteList) {
            session.delete(toDelete);
        }
        session.flush();
        session.getTransaction().commit();

    }

    public abstract Class getClassType();
}
