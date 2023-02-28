package com.system.dao.impl;

import com.system.dao.SoldProductDAO;
import com.system.model.Order;
import com.system.model.Product;
import com.system.model.SoldProduct;
import com.system.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class SoldProductDAOImpl implements SoldProductDAO {

    private static final String SQL_QUERY_GET_ORDER_BY_ID = "select order from Order order where order.id = :ID";

    private final static String SQL_QUERY_GET_ALL_BY_ORDER = "select soldProduct from SoldProduct soldProduct where soldProduct.orderID =: ORDER";


    private static final String ID_PARAM = "ID";
    private static final String ORDER_PARAM = "ORDER";


    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void addSoldProduct(List<Product> productList, Order order) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            for (Product product : productList) {
                session.save(SoldProduct.builder()
                        .orderID(order.getId())
                        .productID(product.getId())
                        .count(product.getCount())
                        .build());
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<SoldProduct> getSoldProductByOrder(Order order) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(SQL_QUERY_GET_ALL_BY_ORDER, SoldProduct.class)
                    .setParameter(ORDER_PARAM, order.getId())
                    .list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void deleteProductsByOrder(Order order) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            List<SoldProduct> soldProducts = getSoldProductByOrder(order);
            for (SoldProduct soldProduct : soldProducts){
                session.delete(soldProduct);
            }
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
