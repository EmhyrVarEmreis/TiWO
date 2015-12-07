package dao;

import model.CRUDOperationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractCRUDTest<T> {

    protected ProductDao productDao = new ProductDao();
    protected ShopDao shopDao = new ShopDao();
    protected UserDao userDao = new UserDao();
    protected ShoppingHistoryDao shoppingHistoryDao = new ShoppingHistoryDao();

    @Before
    @After
    public void cleanTables() throws CRUDOperationException {
        shopDao.deleteAll();
        productDao.deleteAll();
        userDao.deleteAll();
        shoppingHistoryDao.deleteAll();
    }

    @Test
    public abstract void saveGetDeleteTest() throws CRUDOperationException;

    @Test
    public abstract void saveListDeleteTest() throws CRUDOperationException;

    @Test
    public abstract void saveUpdateGetDeleteTest() throws CRUDOperationException;

    protected abstract T createObject();

}
