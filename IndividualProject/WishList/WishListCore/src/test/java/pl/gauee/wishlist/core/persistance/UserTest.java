/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.persistance;

import java.util.Iterator;
import java.util.List;
import junit.framework.TestCase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.gauee.wishlist.core.utils.HibernateUtil;

/**
 *
 * @author gauee
 */
public class UserTest extends TestCase {

    public UserTest(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown(); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void testGetUserList() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Session session = sessionFactory.openSession();

        List users = session.createCriteria(WishUser.class).list();
        System.out.println("Users list size: " + users.size());
        Iterator it = users.iterator();
        while (it.hasNext()) {
            WishUser u = (WishUser) it.next();
            System.out.println(u);
        }

        session.close();

        assertEquals(1, 1);
    }
}
