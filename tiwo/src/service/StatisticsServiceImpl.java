package service;

import model.Product;
import model.User;

import java.util.Date;
import java.util.List;

public class StatisticsServiceImpl implements StatisticsService {
    @Override
    public List<Product> getBestSellingProducts(Date from, Date to, int count) {
        throw new UnsupportedOperationException("Method not supported");
    }

    @Override
    public List<User> getBestBuyersByValue(int count) {
        throw new UnsupportedOperationException("Method not supported");
    }

    @Override
    public List<User> getBestBuyersByOperations(int count) {
        throw new UnsupportedOperationException("Method not supported");
    }
}
