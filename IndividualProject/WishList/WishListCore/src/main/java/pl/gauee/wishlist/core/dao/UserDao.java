/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.dao;

import java.util.List;
import pl.gauee.wishlist.core.api.UserApi;
import pl.gauee.wishlist.core.persistance.WishItem;
import pl.gauee.wishlist.core.persistance.WishUser;

/**
 *
 * @author gauee
 */
public class UserDao extends BaseDao implements UserApi {

    public WishItem getById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private Class classType = WishUser.class;

    public Class getClassType() {
        return classType;
    }

    public boolean isUserExist(String login) {
        return true;
    }
}
