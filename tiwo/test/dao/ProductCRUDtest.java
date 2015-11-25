/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.Iterator;
import model.CRUDOperationException;
import model.Product;
import org.junit.Test;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class ProductCRUDtest extends AbstractCRUDTest {

    private ProductDao productDao = new ProductDao();

    @Test
    public void saveGetDeleteTest() throws CRUDOperationException {
        //Save
        Product product = createObject();
        Long id = productDao.save(product);

        //Get
        Product fromDb = productDao.get(id);
        validate(product, fromDb);

        //Delete
        productDao.delete(fromDb);
        fromDb = productDao.get(id);
        assertNull(fromDb);
    }

    @Override
    public void saveListDeleteTest() throws CRUDOperationException{
       
        List<Product> list = new LinkedList<Product>(); 
        list.add(createObject());
        list.add(createObject());
        list.add(createObject());
        
        //Save list   
        LinkedList<Long> idList = new LinkedList<Long>();
        idList = productDao.saveAll(list); 

        //Get list
        List<Product> listFromDb = new LinkedList<Product>();
        listFromDb = productDao.list();
        
        assertEquals(list.size()+5,listFromDb.size());

        //Delete list
        for (Iterator<Product> iter = listFromDb.listIterator(); iter.hasNext();){
            Product p = iter.next();
            if (idList.contains(p.getId())) {
                productDao.delete(p);
            }
        }
        Product fromDb;
        for (int i=0; i<idList.size();i++){
            fromDb = productDao.get(idList.get(i));
            assertNull(fromDb);
        }   
    }

    @Override
    public void saveUpdateGetDeleteTest()throws CRUDOperationException {
        
        //Save
        Product product = createObject();
        Long id = productDao.save(product); 
         
       //Get
        Product fromDb = productDao.get(id);
        validate(product, fromDb);
        
        //Update
        Product product2 = createObject();
        product2.setName("Updated_name");
        product2.setPrice(2.7);
        product.setName("Updated_name");
        product.setPrice(2.7);
        productDao.update(product);
        
        //Get after update
        fromDb = productDao.get(id);
        validate(product2, fromDb);
        
        //Delete
        productDao.delete(fromDb);
        fromDb = productDao.get(id);
        
        assertNull(fromDb);

    }

    protected Product createObject() {
        Product p = new Product();
        p.setName("name");
        p.setPrice(1.12);
        return p;
    }

    private void validate(Product expected, Product acrual) {
        assertEquals(expected.getName(), acrual.getName());
        assertEquals(expected.getPrice(), acrual.getPrice());
    }


}
