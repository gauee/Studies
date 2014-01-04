/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.dao;

import pl.gauee.wishlist.utils.persistance.WishItem;

/**
 *
 * @author gauee
 */
class ItemDao extends BaseDao<WishItem> {

    public static final Class<WishItem> classType = WishItem.class;

    @Override
    public Class getClassType() {
        return classType;
    }
}
