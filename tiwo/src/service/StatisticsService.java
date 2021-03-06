package service;

import model.Product;
import model.User;
import java.util.Date;
import java.util.List;

public interface StatisticsService {
    /**
     * Najlepiej sprzedace sie produkty w danym okresie (ilosc)
     * @param from
     * @param to
     * @param count
     * @return
     */
    List<Product> getBestSellingProducts(Date from, Date to, int count);

    /**
     * Najwi�cej wydajacy klienci
     * @param count
     * @return
     */
    List<User>  getBestBuyersByValue(int count);

    /**
     * Najcz�sciej kupujacy
     * @param count
     * @return
     */
    List<User> getBestBuyersByOperations(int count);
}
