package com.system.service.impl;


import com.system.dao.SoldProductDAO;
import com.system.dao.impl.SoldProductDAOImpl;
import com.system.model.Order;
import com.system.model.Product;
import com.system.model.SoldProduct;
import com.system.service.SoldProductService;

import java.util.List;

public class SoldProductServiceImpl implements SoldProductService {

    private final SoldProductDAO soldProductDAO = new SoldProductDAOImpl();

    @Override
    public void addSoldProduct(List<Product> productList, Order order) {
        soldProductDAO.addSoldProduct(productList, order);
    }

    @Override
    public List<SoldProduct> getSoldProductByOrder(Order order) {
        return soldProductDAO.getSoldProductByOrder(order);
    }

    @Override
    public void deleteProductsByOrder(Order order) {
        soldProductDAO.deleteProductsByOrder(order);
    }
}
