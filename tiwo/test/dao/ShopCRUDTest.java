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
        
        List<Shop> list = new LinkedList<Shop>(); 
        list.add(createObject());
        list.add(createObject());
        list.add(createObject());

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
    public void saveListDeleteTest() throws CRUDOperationException{
        int setSize = 2;
        
        List<Shop> list = new LinkedList<Shop>(); 
        list.add(createObject());
        list.add(createObject());
        list.add(createObject());
        
        Set<Product> products = createProducts(setSize);
        for (Shop s : list){
            s.setProducts(products);
        }
        
        //Save list
        LinkedList<Long> idList = new LinkedList<Long>();
        idList = shopDao.saveAll(list); 
        
        //Get list
        List<Shop> listFromDb = new LinkedList<Shop>();
        listFromDb = shopDao.list();
        
        assertEquals(list.size()+2,listFromDb.size());
  
        //Delete list
        for (Iterator<Shop> iter = listFromDb.listIterator(); iter.hasNext();){
            Shop p = iter.next();
            if (idList.contains(p.getId())) {
                shopDao.delete(p);
            }
        }
        Shop fromDb;
        for (int i=0; i<idList.size();i++){
            fromDb = shopDao.get(idList.get(i));
            assertNull(fromDb);
        }         
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
        shop.setName("NewShopName");
        for (Product P : products){
            P.setName("New_name");
            P.setPrice(2.7);
        }
        shop.setProducts(products);
        shopDao.update(shop);
        
        Shop shop2 = createObject();
        shop2.setName("NewShopName");
        shop2.setProducts(products);
        
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
