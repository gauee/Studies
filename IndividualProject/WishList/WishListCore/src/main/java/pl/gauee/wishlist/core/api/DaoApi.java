/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.api;

import java.util.List;
import pl.gauee.wishlist.utils.persistance.WishObject;

/**
 *
 * @author gauee
 */
public interface DaoApi<T extends WishObject> {

    public T create(T objectToSave);

    public T getById(Long id);

    public List<T> getAll();

    public Long getCount();

    public T update(T objectToUpdate);

    public void delete(T objectToDelete);

    public void deleteAll();
}
