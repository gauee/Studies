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
public class ListDaoTest extends TestCase {

    public ListDaoTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testGetClassType() {
    }

    public void testGetListById() {
    }

    public void testCreateList() {
    }

    public void testDeleteList() {
        ListDao dao = new ListDao();
        long listCount = dao.getCount();
        dao.deleteList(6L);
        assertEquals(listCount - 1, dao.getCount().longValue());

    }
}
