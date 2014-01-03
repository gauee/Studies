/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.dao;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.classic.Session;
import pl.gauee.wishlist.core.api.DaoApi;
import pl.gauee.wishlist.core.api.WishObject;
import pl.gauee.wishlist.core.persistance.WishItem;
import pl.gauee.wishlist.core.utils.HibernateUtil;
import static pl.gauee.wishlist.core.utils.HibernateUtil.getNewSession;

/**
 *
 * @author gauee
 */
public abstract class BaseDao implements DaoApi {

    public WishItem create(WishItem object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public WishItem update(WishItem object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    public T create(T object) {
//        Session session = HibernateUtil.getNewSession();
//        Serializable id = session.save(object);
//        session.close();
//
//        return object;
//
//    }
//
//    public T getById(Long id) {
//        Session session = HibernateUtil.getNewSession();
//        Object o = session.get(getClassType(), id);
//        session.close();
//        return (T) getClassType().cast(o);
//
//    }
//
//    public List<T> getAll() {
//        return new LinkedList<T>();
//
//    }
//
//    public T update(T object) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    public void delete(Long id) {
//        Session session = getNewSession();
//        session.delete(getById(id));
//
//    }
//
//    public void deleteAll() {
//    }
//
//    public Class getClassType() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
