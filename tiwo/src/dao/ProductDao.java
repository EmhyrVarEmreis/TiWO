package dao;

import model.Product;

public class ProductDao extends AbstractCRUD<Product> {

    public ProductDao() {
        super();
    }

    @Override
    protected String getListQuery() {
        return "FROM Product";
    }

    @Override
    protected Class getRequiredClass() {
        return Product.class;
    }

}
