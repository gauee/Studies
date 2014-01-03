/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.api;

import java.io.Serializable;

/**
 *
 * @author gauee
 */
public interface WishObject extends Serializable {

    public void setId(long id);

    public long getId();
}
