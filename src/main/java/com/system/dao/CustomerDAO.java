package com.system.dao;

import com.system.model.Customer;

import java.util.List;

public interface CustomerDAO {

    Customer getById(Integer id);

    Customer getByPhone(String phone);

    void addCustomer(Customer customer);

    List<Customer> getAll();

    void delete(Customer customer);

    void update(Customer customer);

}
