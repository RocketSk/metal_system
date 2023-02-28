package com.system.dao.impl;

import com.system.dao.CompanyDAO;
import com.system.model.Company;
import com.system.model.Customer;
import com.system.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CompanyDAOImpl implements CompanyDAO {

    private final static String SQL_QUERY_GET_ALL_COMPANY = "From " + Company.class.getSimpleName();

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public List<Company> getAll() {
        try (Session session = sessionFactory.openSession()){
            return session.createQuery(SQL_QUERY_GET_ALL_COMPANY, Company.class).list();
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void update(Company company) {
        Transaction transaction = null;
        Company newCompany = getAll().get(0);
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.evict(newCompany);
            newCompany.setId(company.getId());
            newCompany.setName(company.getName());
            newCompany.setPhoneNumber(company.getPhoneNumber());
            newCompany.setAddress(company.getAddress());
            newCompany.setInn(company.getInn());
            newCompany.setIban(company.getIban());
            session.update(newCompany);
            transaction.commit();
        } catch (HibernateException e) {
            assert transaction != null;
            transaction.rollback();
            e.printStackTrace();
        }
    }

}
