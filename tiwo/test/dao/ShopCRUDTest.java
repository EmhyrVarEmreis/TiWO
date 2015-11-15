package dao;

import model.Product;
import model.Shop;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ShopCRUDTest extends AbstractCRUDTest {

    private ShopDao shopDao = new ShopDao();
    ProductDao productDao = new ProductDao();

    @Override
    public void saveGetDeleteTest() {
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
    public void saveUpdateGetDeleteTest() {

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
            Long id = productDao.save(p);
            p = productDao.get(id);
            products.add(p);
        }

        return products;
    }
}
