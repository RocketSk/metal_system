package com.system.service.impl;

import com.system.dao.OrderDAO;
import com.system.dao.impl.OrderDAOImpl;
import com.system.model.Order;
import com.system.model.Product;
import com.system.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO = new OrderDAOImpl();

    @Override
    public void deleteOrder(Order order) {
        orderDAO.deleteOrder(order);
    }

    @Override
    public List<Order> getAll() {
        return orderDAO.getAll();
    }

    @Override
    public Order getById(Integer id) {
        return orderDAO.getById(id);
    }

    @Override
    public void addOrder(List<Product> productList, Order order) {
        orderDAO.addOrder(productList, order);
    }
}
