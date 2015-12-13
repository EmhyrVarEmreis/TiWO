/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;

import model.CRUDOperationException;
import model.Product;
import org.junit.Test;

import java.util.List;
import model.Shop;
import model.ShoppingHistory;
import model.User;
import static org.junit.Assert.*;

/**
 *
 * @author Jacek
 */
public class StatisticsServiceImplTest extends AbstractStatisticsServiceTest {
    StatisticsServiceImpl stat = new StatisticsServiceImpl();
    
    @Override
    public void getBestSellingProductsTest() throws CRUDOperationException{
        
    }
    @Override
    public void getBestBuyersByValueTest() throws CRUDOperationException{
        List<ShoppingHistory> list = new LinkedList<ShoppingHistory>(Arrays.asList(createObject("Bogdan", "b1", "productName",1)));
        list.add(createObject("Adam","b2", "ProductName",25.5));
        list.add(createObject("Marta","b2", "ProductName",9.99));
        list.add(createObject("Bogdan","b2", "ProductName",70.75));
        list.add(createObject("Marta","b2", "ProductName",12.59));
        list.add(createObject("Bogdan","b2", "ProductName",120.12));
        list.add(createObject("Zyta","b2", "ProductName",0));
        stat.list=list;
        //Oczekiwane wyniki to: Bogdan->Adam->Marta->Zyta
        //Dla 0 metoda ma zwracać null, dla arg. wiekszego od historii zakupów - posortowana calosc
        List<User> list1 = new LinkedList<User>(Arrays.asList(list.get(0).getUser()));
        assertEquals(list1,stat.getBestBuyersByValue(1));
        list1.add(list.get(1).getUser());
        assertEquals(list1,stat.getBestBuyersByValue(2));
        assertNull(stat.getBestBuyersByValue(0));
        list1.add(list.get(2).getUser());
        list1.add(list.get(6).getUser());
        assertEquals(list1,stat.getBestBuyersByValue(100));
        
    }
     @Override
    public void getBestBuyersByOperationsTest() throws CRUDOperationException{
        List<ShoppingHistory> list = new LinkedList<ShoppingHistory>(Arrays.asList(createObject("Marta", "b1", "productName",1)));
        list.add(createObject("Adam","b2", "ProductName",25.5));
        list.add(createObject("Marta","b2", "ProductName",9.99));
        list.add(createObject("Adam","b2", "ProductName",70.75));
        list.add(createObject("Marta","b2", "ProductName",12.59));
        list.add(createObject("Bogdan","b2", "ProductName",120.12));
        list.add(createObject("Zyta","b2", "ProductName",0));
        stat.list=list;
        //Oczekiwane wyniki to: Marta -> Adam -> Bogdan/Zyta
        //Dla 0 metoda ma zwracać null, dla arg. wiekszego od historii zakupów - posortowana calosc
        List<User> list1 = new LinkedList<User>(Arrays.asList(list.get(0).getUser()));
        assertEquals(list1,stat.getBestBuyersByOperations(1));
        list1.add(list.get(1).getUser());
        assertEquals(list1,stat.getBestBuyersByOperations(2));
        assertNull(stat.getBestBuyersByOperations(0));
        list1.add(list.get(5).getUser());
        list1.add(list.get(6).getUser());
        assertEquals(list1,stat.getBestBuyersByOperations(100)); 
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
    protected ShoppingHistory createObject(String userLogin, String shopName, String productName, double price) {
        ShoppingHistory sh = new ShoppingHistory();
        sh.setBuyTime(new Date());
        sh.setUser(new User(userLogin));
        sh.setShop(new Shop(shopName, new HashSet()));
        sh.setProduct(new Product(productName, price));
        return sh;
    }
    
    

    
}
