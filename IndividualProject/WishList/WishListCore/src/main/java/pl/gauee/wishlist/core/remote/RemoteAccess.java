/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.remote;

import java.util.LinkedList;
import java.util.List;
import pl.gauee.wishlist.core.dao.DaoDistributor;
import pl.gauee.wishlist.utils.api.ItemApi;
import pl.gauee.wishlist.utils.api.ListApi;
import pl.gauee.wishlist.utils.api.UserApi;
import pl.gauee.wishlist.utils.persistance.WishItem;
import pl.gauee.wishlist.utils.persistance.WishList;
import pl.gauee.wishlist.utils.persistance.WishUser;
import pl.gauee.wishlist.utils.remote.RemoteAccessApi;

/**
 *
 * @author gauee
 */
public class RemoteAccess implements RemoteAccessApi {

    public long testBySquare(int x) {
        return x * x;
    }

    public boolean authenticateUser(String userLogin, String userPassHash) {
        UserApi userApi = getUserApi();
        return userApi.authenticateUserWithPassHash(userLogin, userPassHash);
    }

    public boolean isUserNameExists(String userName) {
        UserApi userApi = getUserApi();
        return userApi.isUserExist(userName);
    }

    public WishUser createNewWishUser(WishUser wishUser) {
        UserApi userApi = getUserApi();
        wishUser = userApi.createUser(wishUser);

        return wishUser;
    }

    public WishUser getUserByLogin(String userName) {
        UserApi userApi = getUserApi();
        WishUser wishUser = userApi.getUserByLogin(userName);
        return wishUser;
    }

    public List<WishList> getListOwnToUser(String userName) {
        UserApi userApi = getUserApi();
        WishUser wishUser = userApi.getUserByLogin(userName);
        return new LinkedList<WishList>(wishUser.getUserLists());
    }

    public boolean createNewListForUser(WishList list, String userName) {
        UserApi userApi = getUserApi();
        WishUser user = userApi.getUserByLogin(userName);
        list = createList(list);
        user.getUserLists().add(list);
        return userApi.updateUser(user);
    }

    public boolean updateUser(WishUser user) {
        UserApi userApi = getUserApi();
        return userApi.updateUser(user);
    }

    public boolean joinTwoUsersAsFriends(WishUser user1, WishUser user2) {
        UserApi userApi = getUserApi();
        return userApi.joinTwoUserAsFriends(user1, user2);
    }

    public void deleteFriendship(WishUser user, WishUser userToDelete) {
        UserApi userApi = getUserApi();
        user.getUserFriends().remove(userToDelete);
        userToDelete.getUserFriends().remove(user);

        userApi.updateUser(user);
        userApi.updateUser(userToDelete);
    }

    public List<WishUser> getAllUsers() {
        UserApi userApi = getUserApi();
        return userApi.getAllUsers();
    }

    public WishList getList(Long listId) {
        ListApi listApi = getListApi();
        return listApi.getListById(listId);
    }

    public boolean addListToUser(WishList list, String userName) {
        UserApi useApi = getUserApi();
        return useApi.addListToUser(list, userName);
    }

    public WishList createList(WishList list) {
        ListApi listApi = getListApi();
        return listApi.createList(list);
    }

    public void deleteList(long listId) {
        ListApi listApi = getListApi();
        listApi.deleteList(listId);
    }

    public WishItem createItem(WishItem item) {
        ItemApi itemApi = getItemApi();
        return itemApi.createItem(item);
    }

    public void addItemToList(WishItem wishItem, long listId) {
        ListApi listApi = getListApi();
        listApi.addItemToList(wishItem, listId);
    }

    public void updateList(WishList wishList) {
        ListApi listApi = getListApi();
        listApi.updateList(wishList);
    }

    public void setItemBought(long itemId) {
        getItemApi().setItemBougth(itemId);
    }

    public void setItemBoughtCancel(long itemId) {
        getItemApi().setItemBougthCancel(itemId);
    }

    public void deleteItem(long itemId) {
        getItemApi().deleteItem(itemId);
    }

    
//    Additional methods
    private ListApi getListApi() {
        return DaoDistributor.getInstance().getListApi();
    }

    private UserApi getUserApi() {
        return DaoDistributor.getInstance().getUserApi();
    }

    private ItemApi getItemApi() {
        return DaoDistributor.getInstance().getItemApi();
    }
}