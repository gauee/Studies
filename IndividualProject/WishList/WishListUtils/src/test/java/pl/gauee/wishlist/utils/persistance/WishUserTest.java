/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.utils.persistance;

import java.util.ArrayList;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;

/**
 *
 * @author gauee
 */
public class WishUserTest extends TestCase {

    public WishUserTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testEquals() {
        String login = new String("gauee");
        String login2 = new String("gauee");
        WishUser wishUser = new WishUser(login, null, null, null, null, null);
        WishUser wishUser2 = new WishUser(login2, null, null, null, null, null);

        wishUser.setId(6);
        wishUser2.setId(6);

        assertEquals(true, wishUser.equals(wishUser2));
        List<WishUser> users = new ArrayList<WishUser>();
        users.add(wishUser);

        assertEquals(1, users.size());

        users.remove(wishUser2);

        assertEquals(0, users.size());


    }

    public void testHashCode() {
    }

    public void testToString() {
    }

    public void testGetId() {
    }

    public void testSetId() {
    }

    public void testGetLogin() {
    }

    public void testSetLogin() {
    }

    public void testGetName() {
    }

    public void testSetName() {
    }

    public void testGetSurname() {
    }

    public void testSetSurname() {
    }

    public void testGetPassHash() {
    }

    public void testSetPassHash() {
    }

    public void testGetEmail() {
    }

    public void testSetEmail() {
    }

    public void testGetMsisdn() {
    }

    public void testSetMsisdn() {
    }

    public void testGetUserLists() {
    }

    public void testSetUserLists() {
    }

    public void testGetUserFriends() {
    }

    public void testSetUserFriends() {
    }
}
