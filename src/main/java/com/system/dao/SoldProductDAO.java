package com.system.dao;

import com.system.model.Order;
import com.system.model.Product;
import com.system.model.SoldProduct;

import java.util.List;

public interface SoldProductDAO {

    void addSoldProduct (List<Product> productList, Order order);

    List<SoldProduct> getSoldProductByOrder(Order order);

    void deleteProductsByOrder(Order order);

}
