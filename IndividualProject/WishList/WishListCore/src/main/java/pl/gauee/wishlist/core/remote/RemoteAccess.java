/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.remote;

import java.util.LinkedList;
import java.util.List;
import pl.gauee.wishlist.core.dao.DaoDistributor;
import pl.gauee.wishlist.utils.api.UserApi;
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
        UserApi userApi = DaoDistributor.getInstance().getUserApi();
        return userApi.authenticateUserWithPassHash(userLogin, userPassHash);
    }

    public boolean isUserNameExists(String userName) {
        UserApi userApi = DaoDistributor.getInstance().getUserApi();
        return userApi.isUserExist(userName);
    }

    public WishUser createNewWishUser(WishUser wishUser) {
        UserApi userApi = DaoDistributor.getInstance().getUserApi();
        wishUser = userApi.createUser(wishUser);

        return wishUser;
    }

    public WishUser getUserByLogin(String userName) {
        UserApi userApi = DaoDistributor.getInstance().getUserApi();
        WishUser wishUser = userApi.getUserByLogin(userName);
        return wishUser;
    }

    public List<WishList> getListOwnToUser(String userName) {
        UserApi userApi = DaoDistributor.getInstance().getUserApi();
        WishUser wishUser = userApi.getUserByLogin(userName);
        return new LinkedList<WishList>(wishUser.getUserLists());
    }

    public boolean createNewListForUser(WishList list, String userName) {
        UserApi userApi = DaoDistributor.getInstance().getUserApi();
        WishUser user = userApi.getUserByLogin(userName);
        user.getUserLists().add(list);
        return userApi.updateUser(user);
    }

    public boolean updateUser(WishUser user) {
        UserApi userApi = DaoDistributor.getInstance().getUserApi();
        return userApi.updateUser(user);
    }

    public boolean joinTwoUsersAsFriends(WishUser user1, WishUser user2) {
        UserApi userApi = DaoDistributor.getInstance().getUserApi();
        return userApi.joinTwoUserAsFriends(user1, user2);
    }
}
