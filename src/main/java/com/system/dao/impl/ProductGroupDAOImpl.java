package com.system.dao.impl;

import com.system.dao.ProductGroupDAO;
import com.system.model.Product;
import com.system.model.ProductGroup;
import com.system.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ProductGroupDAOImpl implements ProductGroupDAO {

    private final static String SQL_QUERY_GET_ALL_PRODUCT_GROUP = "From " + ProductGroup.class.getSimpleName();
    private static final String SQL_QUERY_GET_PRODUCT_GROUP_BY_ID = "select productGroup from ProductGroup productGroup where productGroup.id = :ID";
    private final static String SQL_QUERY_GET_PRODUCT_GROUP_BY_NAME = "select productGroup from ProductGroup productGroup where productGroup.name = :NAME";

    private final static String ID_PARAM = "ID";
    private final static String NAME_PARAM = "NAME";

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public ProductGroup getById(Integer id) {
        return getProductById(id);
    }

    @Override
    public void addProductGroup(ProductGroup productGroup) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(productGroup);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<ProductGroup> getAll() {
        try (Session session = sessionFactory.openSession()){
            return session.createQuery(SQL_QUERY_GET_ALL_PRODUCT_GROUP, ProductGroup.class).list();
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void delete(ProductGroup productGroup) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(productGroup);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ProductGroup productGroup) {
        Transaction transaction = null;
        ProductGroup newProductGroup = getById(productGroup.getId());
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.evict(newProductGroup);
            newProductGroup.setId(productGroup.getId());
            newProductGroup.setName(productGroup.getName());
            session.update(newProductGroup);
            transaction.commit();
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            e.printStackTrace();
        }
    }

    private ProductGroup getProductById(Integer id){
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(SQL_QUERY_GET_PRODUCT_GROUP_BY_ID, ProductGroup.class)
                    .setParameter(ID_PARAM, id)
                    .uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return new ProductGroup();
    }

    private ProductGroup getProductsGroupByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(SQL_QUERY_GET_PRODUCT_GROUP_BY_NAME, ProductGroup.class)
                    .setParameter(NAME_PARAM, name)
                    .uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return new ProductGroup();
    }

}
