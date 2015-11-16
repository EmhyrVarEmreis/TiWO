/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.CRUDOperationException;
import model.Product;
import org.junit.Test;

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
    public void saveListDeleteTest() {

    }

    @Override
    public void saveUpdateGetDeleteTest() {

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
