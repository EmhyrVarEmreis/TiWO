/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;

import model.CRUDOperationException;
import model.Product;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ProductCRUDtest extends AbstractCRUDTest {


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
        assertNull(productDao.get(id));
    }

    @Override
    public void saveListDeleteTest() throws CRUDOperationException {

        List<Product> list = new ArrayList<>(3);
        list.add(createObject());
        list.add(createObject());
        list.add(createObject());

        //Save list   
        List<Long> idList = productDao.saveAll(list);

        //Get list
        List<Product> listFromDb = productDao.list();
        assertEquals(list.size(), listFromDb.size());

        //Delete list
        for (Product p : list) {
            if (idList.contains(p.getId())) {
                productDao.delete(p);
            }
        }

        for (int i = 0; i < idList.size(); i++) {
            assertNull(productDao.get(idList.get(i)));
        }
    }

    @Override
    public void saveUpdateGetDeleteTest() throws CRUDOperationException {

        //Save
        Product product = createObject();
        Long id = productDao.save(product);

        //Get
        validate(product, productDao.get(id));

        //Update
        Product newProduct = new Product();
        newProduct.setId(product.getId());
        newProduct.setName("Updated_name");
        newProduct.setPrice(2.7);
        productDao.update(newProduct);

        //Get after update
        Product fromDb = productDao.get(id);
        validate(newProduct, fromDb);

        //Delete
        productDao.delete(fromDb);
        assertNull(productDao.get(id));
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
