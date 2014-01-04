package pl.gauee.wishlist.core.dao;

import java.util.List;
import pl.gauee.wishlist.utils.api.ListApi;
import pl.gauee.wishlist.utils.persistance.WishList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author gauee
 */
class ListDao extends BaseDao<WishList> implements ListApi {

    public static final Class<WishList> classType = WishList.class;

    @Override
    public Class getClassType() {
        return classType;
    }

    public List<WishList> getListOwnedToUser(Long userId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
