/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.dao;

import java.sql.Timestamp;
import pl.gauee.wishlist.utils.api.ItemApi;
import pl.gauee.wishlist.utils.persistance.WishItem;

/**
 *
 * @author gauee
 */
class ItemDao extends BaseDao<WishItem> implements ItemApi {

    public static final Class<WishItem> classType = WishItem.class;

    @Override
    public Class getClassType() {
        return classType;
    }

    public WishItem createItem(WishItem item) {
        return super.create(item);
    }

    void removeItem(WishItem item) {
        super.delete(item);
    }

    public void deleteItem(long itemId) {
        removeItem(getById(itemId));
    }

    public void setItemBougth(long itemId) {
        WishItem item = super.getById(itemId);
        item.setBought(true);
        item.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        super.update(item);
    }

    public void setItemBougthCancel(long itemId) {
        WishItem item = super.getById(itemId);
        item.setBought(false);
        item.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        super.update(item);
    }
}
