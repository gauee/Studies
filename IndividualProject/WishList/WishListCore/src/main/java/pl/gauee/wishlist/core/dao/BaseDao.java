/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Projections;
import pl.gauee.wishlist.core.api.DaoApi;
import pl.gauee.wishlist.core.api.WishObject;
import pl.gauee.wishlist.core.utils.HibernateUtil;

/**
 *
 * @author gauee
 */
public abstract class BaseDao<T extends WishObject> implements DaoApi<T> {

    public T create(T objectToSave) {
        Session session = HibernateUtil.getNewSession();
        session.beginTransaction();

        Serializable id = session.save(objectToSave);
        session.getTransaction().commit();
        session.close();

        objectToSave.setId((Long) id);
        return objectToSave;
    }

    public T getById(Long id) {
        Session session = HibernateUtil.getNewSession();
        T objectById = (T) session.get(getClassType(), id);
        session.close();
        return objectById;
    }

    public List<T> getAll() {
        Session session = HibernateUtil.getNewSession();
        List<T> list = session.createCriteria(getClassType()).list();
        session.close();
        return list;
    }

    public Long getCount() {
        Session session = HibernateUtil.getNewSession();
        Long count = (Long) session.createCriteria(getClassType()).setProjection(Projections.rowCount()).uniqueResult();

        session.close();
        return count;

    }

    public T update(T object) {
        Session session = HibernateUtil.getNewSession();
        session.beginTransaction();
        session.update(object);
        session.getTransaction().commit();
        session.close();
        return object;
    }

    public void delete(T objectToDelete) {
        Session session = HibernateUtil.getNewSession();
        session.beginTransaction();
        session.delete(objectToDelete);
        session.flush();
        session.getTransaction().commit();
        session.close();
    }

    public void deleteAll() {
        List<T> toDeleteList = getAll();
        Session session = HibernateUtil.getNewSession();
        session.beginTransaction();
        for (WishObject toDelete : toDeleteList) {
            session.delete(toDelete);
        }
        session.flush();
        session.getTransaction().commit();
        session.close();

    }

    public abstract Class getClassType();
}
