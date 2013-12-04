/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.api;

import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pl.gauee.wishlist.core.persistance.WishUser;
import pl.gauee.wishlist.core.utils.HibernateUtil;

/**
 *
 * @author gauee
 */
public class UserApiTest extends TestCase {

    public UserApiTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCreateUser() {
        String[] userParams = new String[]{
            "admin","adminowehaslo","Damian","Gałka"
        };
        
        if(UserApi.isUserExist(userParams[0])){
            UserApi.deleteUser(userParams[0]);
        }
        
        WishUser user = UserApi.createUser("admin", "adminowehaslo", "Damian", "Gałka");
        boolean rslt = UserApi.isUserExist(user.getId());
        
        assertEquals(rslt,true);
    }

    public void testGetUser_String() {
    }

    public void testGetUser_long() {
    }

    public void testUpdateUser() {
    }

    public void testDeleteUser_long() {
    }

    public void testDeleteUser_String() {
    }

    public void testIsUserExist_long() {
        boolean rslt = UserApi.isUserExist(1);
        assertEquals(rslt, true);

        rslt = UserApi.isUserExist(1234567L);
        assertEquals(rslt, false);
    }

    public void testIsUserExist_String() {
        boolean rslt = UserApi.isUserExist("gauee");
        assertEquals(rslt, true);

        rslt = UserApi.isUserExist("niewiem coto bedzie bo to jest random");
        assertEquals(rslt, false);
    }
}
