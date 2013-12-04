/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.api;

import pl.gauee.wishlist.core.persistance.WishItem;
import pl.gauee.wishlist.core.utils.HibernateUtil;

/**
 *
 * @author gauee
 */
public class WishItemApi {

    public static WishItem createItem(String name, String description) {
        WishItem item = new WishItem();
        item.setName(name);
        item.setDescription(description);

        Long id = (Long) HibernateUtil.saveObject(item);
        item.setId(id);

        return item;
    }

    public static WishItem createItem(String name, String description, float price) {
        WishItem item = new WishItem();
        item.setName(name);
        item.setDescription(description);
        item.setPrice(price);

        Long id = (Long) HibernateUtil.saveObject(item);
        item.setId(id);

        return item;
    }

    public static WishItem getItem(Long id) {
        WishItem item = HibernateUtil.getObjectById(WishItem.class, id);
        return item;
    }

    public static boolean isItemExist(Long id) {
        WishItem item = getItem(id);

        return item == null ? false : true;
    }

    public static boolean updateItem(WishItem itemToUpdate) {
        HibernateUtil.saveOrUpdateObject(itemToUpdate);
        return true;
    }

    public static boolean deleteItem(WishItem itemToDelete) {
        HibernateUtil.deleteObject(itemToDelete);

        return true;
    }
}
