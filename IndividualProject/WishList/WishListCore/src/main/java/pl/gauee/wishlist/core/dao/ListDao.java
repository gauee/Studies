package pl.gauee.wishlist.core.dao;


import pl.gauee.wishlist.core.dao.BaseDao;
import pl.gauee.wishlist.utils.persistance.WishList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author gauee
 */
class ListDao extends BaseDao<WishList> {

    public static final Class<WishList> classType = WishList.class;

    @Override
    public Class getClassType() {
        return classType;
    }
}
