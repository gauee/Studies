/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.webapp.factories;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.gauee.wishlist.utils.persistance.WishItemInList;
import pl.gauee.wishlist.utils.persistance.WishList;
import pl.gauee.wishlist.utils.remote.RemoteAccessApi;
import pl.gauee.wishlist.webapp.api.WebListApi;

/**
 *
 * @author gauee
 */
@Component
public class ListAccess implements WebListApi {

    @Autowired
    @Qualifier(value = "proxyBean")
    RemoteAccessApi remoteAccessApi;

    @Override
    public WishList getList(Long listId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<WishList> getListsOwnedToUser(String userName) {
        return remoteAccessApi.getListOwnToUser(userName);
    }

    @Override
    public boolean createNewListForUser(WishList list, String userName) {
        return remoteAccessApi.createNewListForUser(list, userName);
    }

    @Override
    public List<WishList> getDefaultWishList() {
        List<WishList> wishLists = new LinkedList<WishList>();

        List<WishItemInList> itemInListOne = new LinkedList<WishItemInList>();
        itemInListOne.add(new WishItemInList());
        itemInListOne.add(new WishItemInList());

        wishLists.add(new WishList("Pierwsza lista zakupów"));
        itemInListOne = new LinkedList<WishItemInList>();
        itemInListOne.add(new WishItemInList());
        itemInListOne.add(new WishItemInList(true));

        wishLists.add(new WishList("Druga lista zakupów"));

        return wishLists;
    }
}
