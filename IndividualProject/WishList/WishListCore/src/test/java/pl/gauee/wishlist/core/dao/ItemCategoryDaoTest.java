/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.dao;

import junit.framework.TestCase;
import pl.gauee.wishlist.utils.persistance.WishItemCategory;

/**
 *
 * @author gauee
 */
public class ItemCategoryDaoTest extends TestCase {

    public ItemCategoryDaoTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testCRUDMethods() {
        ItemCategoryDao categoryDao = new ItemCategoryDao();
        categoryDao.deleteAll();

        Long initSize = categoryDao.getCount();
        assertEquals(0, initSize.longValue());

        WishItemCategory itemCategory = new WishItemCategory();
        itemCategory.setName("jedzenie");
        assertEquals(0, itemCategory.getId());

        itemCategory = categoryDao.create(itemCategory);

        assertNotSame(0, itemCategory.getId());

        WishItemCategory itemCategory2 = new WishItemCategory();
        itemCategory2.setName("napoje");
        itemCategory2 = categoryDao.create(itemCategory2);

        assertEquals(2, categoryDao.getCount().longValue());

        itemCategory2.setName("napoje gazowane");
        categoryDao.update(itemCategory2);

        WishItemCategory itemCategory3 = categoryDao.getById(itemCategory2.getId());

        assertEquals("napoje gazowane", itemCategory3.getName());

        WishItemCategory itemCategory1 = new WishItemCategory();
        itemCategory1.setName("napoje");

        itemCategory1 = categoryDao.create(itemCategory1);

        assertEquals(3, categoryDao.getCount().longValue());
        categoryDao.delete(itemCategory3);
        assertEquals(2, categoryDao.getCount().longValue());
        categoryDao.deleteAll();
        assertEquals(0, categoryDao.getCount().longValue());

    }
}
