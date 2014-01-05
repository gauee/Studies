/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.dao;

import junit.framework.TestCase;
import pl.gauee.wishlist.utils.persistance.WishUser;

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

    public void testGetUserByLogin() {
    }

    public void testCreateUser() {
    }

    public void testUpdateUser() {
    }

    public void testJoinTwoUserAsFriends() {
        UserDao dao = new UserDao();

        WishUser user1 = dao.getUserByLogin("friend");
        WishUser user2 = dao.getUserByLogin("gauee");

        dao.joinTwoUserAsFriends(user1, user2);
    }
}
