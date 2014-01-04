/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.dao;

import pl.gauee.wishlist.core.api.DaoDistributorApi;
import pl.gauee.wishlist.utils.api.UserApi;

/**
 *
 * @author gauee
 */
public class DaoDistributor implements DaoDistributorApi {

    private static DaoDistributor instance = new DaoDistributor();

    private DaoDistributor() {
    }

    public static DaoDistributor getInstance() {
        return instance;
    }

    public UserApi getUserApi() {
        return new UserDao();
    }
}
