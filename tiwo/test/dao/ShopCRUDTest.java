package dao;

import model.CRUDOperationException;
import model.Product;
import model.Shop;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ShopCRUDTest extends AbstractCRUDTest {


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
        assertNull(shopDao.get(id));
    }

    @Override
    public void saveListDeleteTest() throws CRUDOperationException {
        int setSize = 2;

        List<Shop> list = new ArrayList<>(3);
        list.add(createObject());
        list.add(createObject());
        list.add(createObject());

        Set<Product> products = createProducts(setSize);
        for (Shop s : list) {
            s.setProducts(products);
        }

        //Save list
        List<Long> idList = shopDao.saveAll(list);

        //Get list
        List<Shop> listFromDb = shopDao.list();
        assertEquals(list.size(), listFromDb.size());

        //Delete list
        for (Shop s : listFromDb) {
            if (idList.contains(s.getId())) {
                shopDao.delete(s);
            }
        }
        Shop fromDb;
        for (Long id : idList) {
            fromDb = shopDao.get(id);
            assertNull(fromDb);
        }
    }

    @Override
    public void saveUpdateGetDeleteTest() throws CRUDOperationException {
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
        shop.setName("NewShopName");
        for (Product p : products) {
            p.setName("New_name");
            p.setPrice(2.7);
        }
        shop.setProducts(products);
        shopDao.update(shop);

        Shop newShop = createObject();
        newShop.setName("NewShopName");
        newShop.setProducts(products);

        //Get after update
        fromDb = shopDao.get(id);
        validate(newShop, fromDb);

        //Delete
        shopDao.delete(fromDb);
        assertNull(shopDao.get(id));
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
