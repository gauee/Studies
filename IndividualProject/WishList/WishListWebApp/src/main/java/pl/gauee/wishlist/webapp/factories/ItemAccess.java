/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.webapp.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.gauee.wishlist.utils.persistance.WishItem;
import pl.gauee.wishlist.utils.remote.RemoteAccessApi;
import pl.gauee.wishlist.webapp.api.WebItemApi;

/**
 *
 * @author gauee
 */
@Component
public class ItemAccess implements WebItemApi {

    @Autowired
    @Qualifier(value = "proxyBean")
    RemoteAccessApi remoteAccessApi;

    @Override
    public WishItem createItem(WishItem item) {
        return remoteAccessApi.createItem(item);
    }
}
