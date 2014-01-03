/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.persistance;

import java.io.Serializable;
import junit.framework.TestCase;
import org.hibernate.Session;
import pl.gauee.wishlist.core.utils.HibernateUtil;

/**
 *
 * @author gauee
 */
public class WishItemCategoryTest extends TestCase {

    public WishItemCategoryTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testCreateCategory() {
        WishItemCategory category = new WishItemCategory();
        category.setName("odzież");

        Session session = HibernateUtil.getNewSession();
        session.beginTransaction();
        Serializable serial = session.save(category);
        System.out.println(serial);
        System.out.println(((Long) serial).toString());
        session.getTransaction().commit();
    }
}
