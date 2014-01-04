/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.remote;

import pl.gauee.wishlist.utils.remote.RemoteAccessApi;

/**
 *
 * @author gauee
 */
public class RemoteAccess implements RemoteAccessApi {

    public long testBySquare(int x) {
        return x * x;
    }
}
