/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.api;

/**
 *
 * @author gauee
 */
public interface UserApi extends DaoApi {

    public boolean isUserExist(String login);
}
