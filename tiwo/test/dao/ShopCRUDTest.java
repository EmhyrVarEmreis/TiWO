package dao;

import model.CRUDOperationException;
import model.Product;
import model.Shop;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ShopCRUDTest extends AbstractCRUDTest {

    private ShopDao shopDao = new ShopDao();

    @Override
    public void saveGetDeleteTest() throws CRUDOperationException {
        int setSize = 2;

        //Save
        Set<Product> products = createProducts(setSize);
        Shop shop = createObject();
        shop.setProducts(products);
        Long id = shopDao.save(shop);

        //Get
        Shop fromDb = shopDao.get(id);
        validate(shop, fromDb);

        //Delete
        shopDao.delete(fromDb);
        fromDb = shopDao.get(id);
        assertNull(fromDb);
    }

    @Override
    public void saveListDeleteTest() {
    }

    @Override
    public void saveUpdateGetDeleteTest() throws CRUDOperationException{
        int setSize = 2;
        
        //Save
        Set<Product> products = createProducts(setSize);
        Shop shop = createObject();
        shop.setProducts(products);
        Long id = shopDao.save(shop);
        
        //Get
        Shop fromDb = shopDao.get(id);
        validate(shop, fromDb);
        
        //Update
        shop.setName("newShopName");
        Shop shop2 = createObject();
        shop2.setName("newShopName");
        shop2.setProducts(products);
        shopDao.update(shop);
        
        //Get after update
        fromDb = shopDao.get(id);
        validate(shop2,fromDb);
      
        
        //Delete
        shopDao.delete(fromDb);
        fromDb = shopDao.get(id);
        assertNull(fromDb);


    }

    @Override
    protected Shop createObject() {
        Shop shop = new Shop();
        shop.setName("shopName");
        return shop;
    }

    private void validate(Shop expected, Shop actual) {
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getProducts().size(), actual.getProducts().size());
    }

    private Set<Product> createProducts(int size) {
        Set<Product> products = new HashSet();
        for (int i = 0; i < size; i++) {
            Product p = new Product();
            p.setName("name");
            p.setPrice(1.3);
            products.add(p);
        }
        return products;
    }
}
