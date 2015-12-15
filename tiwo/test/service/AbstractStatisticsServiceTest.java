/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ProductDao;
import dao.ShopDao;
import dao.ShoppingHistoryDao;
import dao.UserDao;
import model.CRUDOperationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Jacek
 */
public abstract class AbstractStatisticsServiceTest<T> {

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
    public abstract void getBestSellingProductsTest() throws CRUDOperationException;

    @Test
    public abstract void getBestBuyersByValueTest() throws CRUDOperationException;

    @Test
    public abstract void getBestBuyersByOperationsTest() throws CRUDOperationException;

    protected abstract T createObject();

}
