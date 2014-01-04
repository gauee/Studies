/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.dao;

import pl.gauee.wishlist.core.persistance.WishItemInList;

/**
 *
 * @author gauee
 */
class ItemInListDao extends BaseDao<WishItemInList> {

    public static final Class<WishItemInList> classType = WishItemInList.class;

    @Override
    public Class getClassType() {
        return classType;
    }
}
