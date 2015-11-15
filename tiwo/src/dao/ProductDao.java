package dao;

import model.Product;

/**
 *
 * @author Paweł Kłapsa
 */
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
