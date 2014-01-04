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
public class BaseDaoTest extends TestCase {

    public BaseDaoTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testCreate() {
    }

    public void testGetById() {
    }

    public void testGetAll() {
    }

    public void testGetCount() {
    }

    public void testUpdate() {
    }

    public void testDelete() {
    }

    public void testDeleteAll() {
    }

    public void testGetClassType() {
    }

    public class BaseDaoImpl extends BaseDao {

        public Class getClassType() {
            return null;
        }
    }
}
