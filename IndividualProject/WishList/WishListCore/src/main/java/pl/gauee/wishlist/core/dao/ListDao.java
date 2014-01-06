package pl.gauee.wishlist.core.dao;

import org.apache.log4j.Logger;
import pl.gauee.wishlist.utils.api.ListApi;
import pl.gauee.wishlist.utils.persistance.WishItem;
import pl.gauee.wishlist.utils.persistance.WishList;
import pl.gauee.wishlist.utils.persistance.WishUser;

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
    private final Logger logger = Logger.getLogger(ListDao.class);

    @Override
    public Class getClassType() {
        return classType;
    }

    public WishList getListById(Long listId) {
        return super.getById(listId);
    }

    public WishList createList(WishList list) {
        return super.create(list);
    }

    public void addItemToList(WishItem wishItem, long listId) {
        WishList list = getListById(listId);
        list.getListItems().add(wishItem);
        updateList(list);
    }

    public void updateList(WishList wishList) {
        super.update(wishList);
    }

    public void deleteList(long listId) {
        WishList list = super.getById(listId);
        UserDao userDao = new UserDao();

        for (WishUser user : list.getListUsers()) {
            user.getUserLists().remove(list);
            userDao.update(user);
        }
        super.delete(list);
    }

    void removeItemFromList(WishItem item, long listId) {
        WishList list = super.getById(listId);
        list.getListItems().remove(item);
        super.update(list);
    }
}
