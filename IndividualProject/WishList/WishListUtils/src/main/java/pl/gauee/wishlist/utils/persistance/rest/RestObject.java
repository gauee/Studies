/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.utils.persistance.rest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import pl.gauee.wishlist.utils.persistance.WishObject;

/**
 *
 * @author gauee
 */
public abstract class RestObject<M, N extends WishObject> implements Serializable {

    private static final long serialVersionUID = 8062745708664909747L;
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public abstract M packTo(N sourceObject);

    public abstract N repackFrom(M sourceObject);

    public List<M> packListTo(Set<N> sourceList) {
        List<M> resultList = new ArrayList<M>();
        if (sourceList == null || sourceList.isEmpty()) {
            return resultList;
        }

        for (N souceObject : sourceList) {
            resultList.add(packTo(souceObject));
        }

        return resultList;
    }

    public Set<N> repackListFrom(List<M> sourceList) {
        Set<N> resultSet = new HashSet<N>();
        if (sourceList == null || sourceList.isEmpty()) {
            return resultSet;
        }

        for (M souceObject : sourceList) {
            resultSet.add(repackFrom(souceObject));
        }

        return resultSet;
    }
}
