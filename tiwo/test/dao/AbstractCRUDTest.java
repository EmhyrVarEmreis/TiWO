package dao;

import model.CRUDOperationException;
import org.junit.Test;

public abstract class AbstractCRUDTest<T> {

    @Test
    public abstract void saveGetDeleteTest() throws CRUDOperationException;

    @Test
    public abstract void saveListDeleteTest();

    @Test
    public abstract void saveUpdateGetDeleteTest();

    protected abstract T createObject();

}
