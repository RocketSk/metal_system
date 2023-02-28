package com.system.dao.impl;

import com.system.dao.OrderDAO;
import com.system.dao.SoldProductDAO;
import com.system.model.Order;
import com.system.model.Product;
import com.system.service.SoldProductService;
import com.system.service.impl.SoldProductServiceImpl;
import com.system.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    private final SoldProductService soldProductService = new SoldProductServiceImpl();

    private final static String SQL_QUERY_GET_ALL_ORDER = "From " + Order.class.getSimpleName();
    private static final String SQL_QUERY_GET_ORDER_BY_ID = "select order from Order order where order.id = :ID";


    private static final String ID_PARAM = "ID";


    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void addOrder(List<Product> productList, Order order) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(order);
            soldProductService.addSoldProduct(productList, order);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(Order order) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(order);
            soldProductService.deleteProductsByOrder(order);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> getAll() {
        try (Session session = sessionFactory.openSession()){
            return session.createQuery(SQL_QUERY_GET_ALL_ORDER, Order.class).list();
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Order getById(Integer id) {
        return getOrderById(id);
    }

    private Order getOrderById(Integer id){
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(SQL_QUERY_GET_ORDER_BY_ID, Order.class)
                    .setParameter(ID_PARAM, id)
                    .uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return new Order();
    }
}
