package dao;

import model.CRUDOperationException;
import model.User;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class UserCrudTest extends AbstractCRUDTest {

    @Ignore
    @Override
    public void saveGetDeleteTest() throws CRUDOperationException {

    }

    @Ignore
    @Override
    public void saveListDeleteTest() throws CRUDOperationException {

    }

    @Test
    public void saveUpdateGetDeleteTest() throws CRUDOperationException {
        //Save
        Long id = userDao.save(createObject());

        //Get and Update
        User u = userDao.get(id);
        assertNotNull(u);
        u.setLogin("changed");
        userDao.update(u);

        u = userDao.get(id);
        assertEquals("changed", u.getLogin());

        //Delete
        userDao.delete(u);
        assertNull(userDao.get(id));
    }

    protected User createObject() {
        User u = new User();
        u.setLogin("login");
        return u;
    }
}
