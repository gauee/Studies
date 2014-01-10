/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.dao;

import pl.gauee.wishlist.utils.persistance.WishItemCategory;

/**
 *
 * @author gauee
 */
class ItemCategoryDao extends BaseDao<WishItemCategory> {

    private static final Class<WishItemCategory> classType = WishItemCategory.class;

    @Override
    public Class getClassType() {
        return classType;
    }
}
