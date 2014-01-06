/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.core.dao;

import junit.framework.TestCase;
import pl.gauee.wishlist.utils.persistance.WishItem;
import pl.gauee.wishlist.utils.persistance.WishList;

/**
 *
 * @author gauee
 */
public class ItemDaoTest extends TestCase {

    public ItemDaoTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testGetClassType() {
    }

    public void testCreateItem() {
        WishItem item = new WishItem();

        item.setName("Produkt1");
        item.setDescription("Testowy produkt");
        item.setPhotoUrl("ścieżka do pliku");
        item.setPrice(3.41);

        ItemDao itemDao = new ItemDao();
        ListDao listDao = new ListDao();


        long countItem = itemDao.getCount();
        itemDao.create(item);
        assertEquals(countItem + 1, itemDao.getCount().longValue());

        WishList list = listDao.getListById(1L);
        list.getListItems().add(item);

        listDao.update(list);


        listDao.removeItemFromList(item, 1L);
        itemDao.removeItem(item);
        assertEquals(countItem, itemDao.getCount().longValue());
    }
}
