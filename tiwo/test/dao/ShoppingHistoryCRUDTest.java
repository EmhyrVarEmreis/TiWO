package dao;

import model.*;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ShoppingHistoryCRUDTest extends AbstractCRUDTest {


    @Ignore
    @Override
    public void saveGetDeleteTest() throws CRUDOperationException {

    }

    @Ignore
    @Override
    public void saveListDeleteTest() throws CRUDOperationException {

    }

    @Test
    public void saveUpdateGetDeleteTest() throws CRUDOperationException {
        String productName = "productName";

        //Save
        List<ShoppingHistory> list = Arrays.asList(createObject("a1", "a2", productName), createObject("b1", "b2", productName));
        List<Long> ids = shoppingHistoryDao.saveAll(list);
        validateAdded(ids);

        ShoppingHistory shoppingHistory = shoppingHistoryDao.get(ids.get(0));
        assertEquals(shoppingHistory.getProduct().getName(), productName);

        //Delete
        shoppingHistoryDao.deleteAll();
        assertEquals(0, shoppingHistoryDao.list().size());
    }

    @Override
    protected Object createObject() {
        ShoppingHistory sh = new ShoppingHistory();
        sh.setBuyTime(new Date());
        sh.setUser(new User("userLogin"));
        sh.setShop(new Shop("shopName", new HashSet()));
        sh.setProduct(new Product("productName", 1.2));
        return sh;
    }

    private void validateAdded(List<Long> ids) throws CRUDOperationException {
        ShoppingHistory sh;
        for (Long id : ids) {
            sh = shoppingHistoryDao.get(id);
            assertNotNull(sh);
        }
    }

    protected ShoppingHistory createObject(String userLogin, String shopName, String productName) {
        ShoppingHistory sh = new ShoppingHistory();
        sh.setBuyTime(new Date());
        sh.setUser(new User(userLogin));
        sh.setShop(new Shop(shopName, new HashSet()));
        sh.setProduct(new Product(productName, 1.2));
        return sh;
    }
}
