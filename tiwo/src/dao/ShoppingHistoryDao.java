package dao;

import model.ShoppingHistory;

public class ShoppingHistoryDao extends AbstractCRUD<ShoppingHistory> {
    @Override
    protected String getListQuery() {
        return "FROM ShoppingHistory";
    }

    @Override
    protected Class getRequiredClass() {
        return ShoppingHistory.class;
    }

}
