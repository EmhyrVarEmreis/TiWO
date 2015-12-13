package service;

import java.util.Arrays;
import model.Product;
import model.User;
import model.CRUDOperationException;

import java.util.*;
import model.ShoppingHistory;

public class StatisticsServiceImpl implements StatisticsService {
    public List<ShoppingHistory> list;
    
    @Override
    public List<Product> getBestSellingProducts(Date from, Date to, int count) {
        throw new UnsupportedOperationException("Method not supported");
    }

    @Override
    public List<User> getBestBuyersByValue(int count){

        if(count <= 0)
            return null;
        else if(count > list.size())
            count = list.size();
        boolean inMap = false;
        Map<User, Double> hm= new HashMap<User, Double>();
        for(int i = 0;i<list.size();i++)
            System.out.println(list.get(i).getUser().getLogin());
        
        
        for(int i =0;i < list.size();i++){
            for (Map.Entry<User, Double> entry : hm.entrySet()) {
                if(list.get(i).getUser().getLogin() == entry.getKey().getLogin() ){
                    entry.setValue((double)entry.getValue()+ list.get(i).getProduct().getPrice());
                    inMap = true;
                }
            }
            if(inMap==false){
                hm.put(list.get(i).getUser(), list.get(i).getProduct().getPrice());
            }
            inMap = false;
                }
        hm = sortByValues(hm);
        for (Map.Entry<User, Double> entry : hm.entrySet()) {
            System.out.println(entry.getKey() +" "+ entry.getValue());
        }
        List<User> listEnd = new ArrayList<User>(hm.keySet());
        while(listEnd.size() > count)
        listEnd.remove(listEnd.size()-1);
          
        return listEnd;
    }

    @Override
    public List<User> getBestBuyersByOperations(int count) {
        if(count <= 0)
            return null;
        else if(count > list.size())
            count = list.size();
        boolean inMap = false;
        Map<User, Integer> hm= new HashMap<User, Integer>();
        for(int i = 0;i<list.size();i++)
            System.out.println(list.get(i).getUser().getLogin());
        
        
        for(int i =0;i < list.size();i++){
            for (Map.Entry<User, Integer> entry : hm.entrySet()) {
                if(list.get(i).getUser().getLogin() == entry.getKey().getLogin() ){
                    entry.setValue((int)entry.getValue()+ 1);
                    inMap = true;
                }
            }
            if(inMap==false){
                hm.put(list.get(i).getUser(), 1);
            }
            inMap = false;
                }
        hm = sortByValues(hm);
        for (Map.Entry<User, Integer> entry : hm.entrySet()) {
            System.out.println(entry.getKey() +" "+ entry.getValue());
        }
        List<User> listEnd = new ArrayList<User>(hm.keySet());
        while(listEnd.size() > count)
        listEnd.remove(listEnd.size()-1);
          
        return listEnd;
    }
       private static HashMap sortByValues(Map map) { 
       List list = new LinkedList(map.entrySet());
       // Defined Custom Comparator here
       Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
               return ((Comparable) ((Map.Entry) (o2)).getValue())
                  .compareTo(((Map.Entry) (o1)).getValue());
            }
       });

       // Here I am copying the sorted list in HashMap
       // using LinkedHashMap to preserve the insertion order
       HashMap sortedHashMap = new LinkedHashMap();
       for (Iterator it = list.iterator(); it.hasNext();) {
              Map.Entry entry = (Map.Entry) it.next();
              sortedHashMap.put(entry.getKey(), entry.getValue());
       } 
       return sortedHashMap;
  }
    
}
