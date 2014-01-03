/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.api;

import java.util.List;
import pl.gauee.wishlist.core.persistance.WishItem;

/**
 *
 * @author gauee
 */
public interface DaoApi<T extends WishItem> {

    public T create(T object);

    public T getById(Long id);

    public List<T> getAll();

    public T update(T object);

    public void delete(Long id);

    public void deleteAll();

    public Class getClassType();
}
