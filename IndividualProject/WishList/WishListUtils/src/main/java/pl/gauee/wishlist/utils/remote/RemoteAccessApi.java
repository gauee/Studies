/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.utils.remote;

import java.util.List;
import pl.gauee.wishlist.utils.persistance.WishItem;
import pl.gauee.wishlist.utils.persistance.WishList;
import pl.gauee.wishlist.utils.persistance.WishUser;

/**
 *
 * @author gauee
 */
public interface RemoteAccessApi {

    public long testBySquare(int x);

    public boolean authenticateUser(String userLogin, String userPassHash);

    public boolean isUserNameExists(String userName);

    public WishUser createNewWishUser(WishUser user);

    public WishUser getUserByLogin(String userName);

    public List<WishList> getListOwnToUser(String userName);

    public boolean createNewListForUser(WishList list, String userName);

    public boolean updateUser(WishUser user);

    public boolean joinTwoUsersAsFriends(WishUser user1, WishUser user2);

    public List<WishUser> getAllUsers();

    public void deleteFriendship(WishUser user, WishUser userToDelete);

    public WishList getList(Long listId);

    public boolean addListToUser(WishList list, String userName);

    public WishList createList(WishList list);

    public void deleteList(long listId);

    public WishItem createItem(WishItem item);

    public void addItemToList(WishItem wishItem, long listId);

    public void updateList(WishList wishList);
}
