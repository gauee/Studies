package pl.gauee.wishlist.core.dao;


import pl.gauee.wishlist.core.dao.BaseDao;
import pl.gauee.wishlist.core.persistance.WishUserGroup;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author gauee
 */
public class UserGroupDao extends BaseDao<WishUserGroup> {

    public static final Class<WishUserGroup> classType = WishUserGroup.class;

    @Override
    public Class getClassType() {
        return classType;
    }
}
