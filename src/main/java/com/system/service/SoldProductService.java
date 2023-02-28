package com.system.service;

import com.system.model.Order;
import com.system.model.Product;
import com.system.model.SoldProduct;

import java.util.List;

public interface SoldProductService {

    void addSoldProduct (List<Product> productList, Order order);

    List<SoldProduct> getSoldProductByOrder(Order order);

    void deleteProductsByOrder(Order order);

}
