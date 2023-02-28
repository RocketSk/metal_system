package com.system.dao.impl;

import com.system.dao.CustomerDAO;
import com.system.model.Customer;
import com.system.model.ProductGroup;
import com.system.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    private final static String SQL_QUERY_GET_ALL_CUSTOMER = "From " + Customer.class.getSimpleName();
    private static final String SQL_QUERY_GET_CUSTOMER_BY_ID = "select customer from Customer customer where customer.id = :ID";
    private final static String SQL_QUERY_GET_CUSTOMER_BY_PHONE = "select customer from Customer customer where customer.phoneNumber = :PHONE";

    private final static String ID_PARAM = "ID";
    private final static String PHONE_PARAM = "PHONE";

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public Customer getById(Integer id) {
        return getCustomerById(id);
    }

    @Override
    public void addCustomer(Customer customer) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> getAll() {
        try (Session session = sessionFactory.openSession()){
            return session.createQuery(SQL_QUERY_GET_ALL_CUSTOMER, Customer.class).list();
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void delete(Customer customer) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(customer);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer getByPhone(String phone){
        return getCustomerByPhone(phone);
    }

    @Override
    public void update(Customer customer) {
        Transaction transaction = null;
        Customer newCustomer = getById(customer.getId());
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.evict(newCustomer);
            newCustomer.setId(customer.getId());
            newCustomer.setName(customer.getName());
            newCustomer.setPhoneNumber(customer.getPhoneNumber());
            newCustomer.setAddress(customer.getAddress());
            newCustomer.setInn(customer.getInn());
            newCustomer.setIban(customer.getIban());
            session.update(newCustomer);
            transaction.commit();
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            e.printStackTrace();
        }
    }

    private Customer getCustomerById(Integer id){
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(SQL_QUERY_GET_CUSTOMER_BY_ID, Customer.class)
                    .setParameter(ID_PARAM, id)
                    .uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return new Customer();
    }

    private Customer getCustomerByPhone(String phone) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(SQL_QUERY_GET_CUSTOMER_BY_PHONE, Customer.class)
                    .setParameter(PHONE_PARAM, phone)
                    .uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return new Customer();
    }

}
