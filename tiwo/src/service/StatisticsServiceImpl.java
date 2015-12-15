package service;

import model.Product;
import model.User;

import java.util.*;

import model.ShoppingHistory;

public class StatisticsServiceImpl implements StatisticsService {

    private List<ShoppingHistory> list;

    public StatisticsServiceImpl(List<ShoppingHistory> list) {
        this.list = list;
    }

    @Override
    public List<Product> getBestSellingProducts(Date from, Date to, int count) {
        throw new UnsupportedOperationException("Method not supported");
    }

    @Override
    public List<User> getBestBuyersByValue(int count) {

        if (count <= 0)
            return null;

        HashMap<User, Double> hm = new HashMap();
        for (ShoppingHistory shoppingHistory : list) {
            System.out.println("Analyzing data for: " + shoppingHistory.getUser().getLogin());
            User u = shoppingHistory.getUser();
            if (hm.containsKey(u)) {
                hm.put(u, hm.get(u) + shoppingHistory.getProduct().getPrice());
            } else {
                hm.put(u, shoppingHistory.getProduct().getPrice());
            }
        }
        return sortAndTransform(hm, count);
    }

    @Override
    public List<User> getBestBuyersByOperations(int count) {
        if (count <= 0)
            return null;

        HashMap<User, Integer> hm = new HashMap();
        for (ShoppingHistory shoppingHistory : list) {
            System.out.println("Analyzing data for: " + shoppingHistory.getUser().getLogin());
            User u = shoppingHistory.getUser();
            if (hm.containsKey(u)) {
                hm.put(u, hm.get(u) + 1);
            } else {
                hm.put(u, 1);
            }
        }
        return sortAndTransform(hm, count);
    }

    private static List<User> sortAndTransform(HashMap<User, ? extends Comparable> hm, int count) {
        hm = sortByValues(hm);
        for (Map.Entry<User, ? extends Comparable> entry : hm.entrySet()) {
            System.out.println("User:" + entry.getKey().getLogin() + " value: " + entry.getValue());
        }
        List<User> listEnd = new ArrayList(hm.keySet());
        return listEnd.subList(0, count > listEnd.size() ? listEnd.size() : count);
    }

    private static HashMap sortByValues(HashMap<User, ? extends Comparable> map) {
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
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

}
