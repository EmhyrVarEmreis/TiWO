package dao;

import org.junit.Test;

public abstract class AbstractCRUDTest<T> {

    @Test
    public abstract void saveGetDeleteTest();

    @Test
    public abstract void saveListDeleteTest();

    @Test
    public abstract void saveUpdateGetDeleteTest();

    protected abstract T createObject();

}
