package com.system.service;

import com.system.model.Customer;

import java.util.List;

public interface CustomerService {

    Customer getById(Integer id);

    Customer getByPhone(String phone);

    void addCustomer(Customer customer);

    List<Customer> getAll();

    void delete(Customer customer);

    void update(Customer customer);

}
