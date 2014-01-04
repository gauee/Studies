/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.dao;

import junit.framework.TestCase;

/**
 *
 * @author gauee
 */
public class UserDaoTest extends TestCase {

    public UserDaoTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testGetClassType() {
    }

    public void testIsUserExist() {
        UserDao dao = new UserDao();
        assertEquals(true, dao.isUserExist("gauee"));
        assertEquals(false, dao.isUserExist("dmaisansaisnasa"));
    }

    public void testAuthenticateUserWithPassHash() {
    }
}
