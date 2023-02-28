package com.system.service.impl;

import com.system.dao.CustomerDAO;
import com.system.dao.impl.CustomerDAOImpl;
import com.system.model.Customer;
import com.system.service.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerDAO customerDAO = new CustomerDAOImpl();

    @Override
    public Customer getById(Integer id) {
        return customerDAO.getById(id);
    }

    @Override
    public Customer getByPhone(String phone) {
        return customerDAO.getByPhone(phone);
    }

    @Override
    public void addCustomer(Customer customer) {
        customerDAO.addCustomer(customer);
    }

    @Override
    public List<Customer> getAll() {
        return customerDAO.getAll();
    }

    @Override
    public void delete(Customer customer) {
        customerDAO.delete(customer);
    }

    @Override
    public void update(Customer customer) {
        customerDAO.update(customer);
    }
}
