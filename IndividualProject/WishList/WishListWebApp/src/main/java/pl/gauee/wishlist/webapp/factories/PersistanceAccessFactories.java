/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.webapp.factories;

import pl.gauee.wishlist.webapp.api.ListApi;
import pl.gauee.wishlist.webapp.api.PersistanceFactoryApi;
import pl.gauee.wishlist.webapp.api.UserApi;

/**
 *
 * @author gauee
 */
public class PersistanceAccessFactories implements PersistanceFactoryApi {

    private static final PersistanceAccessFactories instance = new PersistanceAccessFactories();

    private PersistanceAccessFactories() {
    }

    public static PersistanceAccessFactories getInstance() {
        return instance;
    }

    @Override
    public UserApi getUserApi() {
        return new UserAccess();
    }

    @Override
    public ListApi getListApi() {
        return new ListAccess();
    }
}
