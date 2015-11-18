package dao;

import model.CRUDOperationException;
import org.junit.Test;

public abstract class AbstractCRUDTest<T> {

    @Test
    public abstract void saveGetDeleteTest() throws CRUDOperationException;

    @Test
    public abstract void saveListDeleteTest() throws CRUDOperationException;

    @Test
    public abstract void saveUpdateGetDeleteTest() throws CRUDOperationException;

    protected abstract T createObject();

}
