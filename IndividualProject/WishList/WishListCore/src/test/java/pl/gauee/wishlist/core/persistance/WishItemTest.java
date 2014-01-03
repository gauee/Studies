/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.persistance;

import java.util.HashSet;
import java.util.Set;
import junit.framework.TestCase;
import org.hibernate.Session;
import pl.gauee.wishlist.core.utils.HibernateUtil;

/**
 *
 * @author gauee
 */
public class WishItemTest extends TestCase {

    public WishItemTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testCreateNewItem() {

        Session session = HibernateUtil.getNewSession();

        session.beginTransaction();

        WishItem item = new WishItem();
        item.setName("Kebab");
        item.setDescription("Nie zdrowe gówno");
        item.setPrice(9.50);

        WishItemCategory category = new WishItemCategory();
        category.setName("fastFood");
        Set<WishItem> itemsInCategory = new HashSet<WishItem>();
        itemsInCategory.add(item);
        category.setItemInCategory(itemsInCategory);

        session.save(category);

        session.getTransaction().commit();
    }
}
