package dao;

import model.Shop;

/**
 * @author Pawe³ K³apsa
 */
public class ShopDao extends AbstractCRUD<Shop> {
    @Override
    protected String getListQuery() {
        return "FROM Shop";
    }

    @Override
    protected Class getRequiredClass() {
        return Shop.class;
    }
}
