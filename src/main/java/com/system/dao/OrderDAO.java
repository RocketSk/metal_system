package com.system.dao;

import com.system.model.Order;
import com.system.model.Product;

import java.util.List;

public interface OrderDAO {

    void addOrder (List<Product> productList, Order order);
    void deleteOrder(Order order);
    List<Order> getAll();
    Order getById(Integer id);

}
