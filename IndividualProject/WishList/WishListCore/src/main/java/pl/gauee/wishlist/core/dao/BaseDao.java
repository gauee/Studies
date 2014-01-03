/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Hibernate;
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
        objectToSave.setId((Long) id);

        return objectToSave;
    }

    public WishObject getById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public WishObject update(WishObject object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Class getClassType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
