/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.dao;

import pl.gauee.wishlist.core.persistance.WishItemCategory;

/**
 *
 * @author gauee
 */
class ItemCategoryDao extends BaseDao<WishItemCategory> {

    public static final Class<WishItemCategory> classType = WishItemCategory.class;

    @Override
    public Class getClassType() {
        return classType;
    }
}
