/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.webapp.factories;

import java.util.LinkedList;
import java.util.List;
import pl.gauee.wishlist.utils.decorators.DWishItemInList;
import pl.gauee.wishlist.utils.decorators.DWishList;
import pl.gauee.wishlist.webapp.api.ListApi;

/**
 *
 * @author gauee
 */
public class ListAccess implements ListApi {

    @Override
    public DWishList getList(Long listId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DWishList> getListsOwnedToUser(Long userId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DWishList> getDefaultWishList() {
        List<DWishList> wishLists = new LinkedList<DWishList>();

        List<DWishItemInList> itemInListOne = new LinkedList<DWishItemInList>();
        itemInListOne.add(new DWishItemInList("sok pomaranczowy", "file://", "z tesco", 4.00));
        itemInListOne.add(new DWishItemInList("sok pomidorowy", "file://", "z biedronki", 4.00));

        wishLists.add(new DWishList("Pierwsza lista zakupów", itemInListOne));
        itemInListOne = new LinkedList<DWishItemInList>();
        itemInListOne.add(new DWishItemInList("kable wyskokiego napięcia", "file://", "naprawa samochodu", 87.00));
        DWishItemInList item = new DWishItemInList("świece do samochdu", "file://", "naprawa samochodu", 23.50);
        item.setBougth(true);
        itemInListOne.add(item);

        wishLists.add(new DWishList("Druga lista zakupów", itemInListOne));

        return wishLists;
    }
}
