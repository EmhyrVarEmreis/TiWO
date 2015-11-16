package dao;

import java.util.LinkedList;
import java.util.List;

import model.CRUDOperationException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * @author Paweł Kłapsa
 */
public abstract class AbstractCRUD<T> {

    private static SessionFactory factory;

    public AbstractCRUD() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addResource("product.hbm.xml");
        configuration.addResource("shop.hbm.xml");

        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        factory = configuration.buildSessionFactory(ssrb.build());
    }

    public Long save(T object) throws CRUDOperationException {
        Session session = factory.openSession();
        Transaction tx = null;
        Long id = null;
        try {
            tx = session.beginTransaction();
            id = (Long) session.save(object);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new CRUDOperationException("CRUD Save operation not successful", e);
        } finally {
            session.close();
        }
        return id;
    }

    public List list() throws CRUDOperationException {
        Session session = factory.openSession();
        Transaction tx = null;
        List objects = new LinkedList();
        try {
            tx = session.beginTransaction();
            objects = session.createQuery(getListQuery()).list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new CRUDOperationException("CRUD List operation not successful", e);
        } finally {
            session.close();
        }
        return objects;
    }

    public void update(T object) throws CRUDOperationException {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(object);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new CRUDOperationException("CRUD Update operation not successful", e);
        } finally {
            session.close();
        }
    }

    public void delete(T object) throws CRUDOperationException {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(object);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new CRUDOperationException("CRUD Delete operation not successful", e);
        } finally {
            session.close();
        }
    }

    public T get(Long id) throws CRUDOperationException {
        Session session = factory.openSession();
        Transaction tx = null;
        T returned = null;
        try {
            tx = session.beginTransaction();
            returned = (T) session.get(getRequiredClass(), id);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new CRUDOperationException("CRUD Get operation not successful", e);
        } finally {
            session.close();
        }
        return returned;
    }

    protected abstract String getListQuery();

    protected abstract Class getRequiredClass();

}
