package com.system.service.impl;

import com.system.model.Order;
import com.system.model.OrderDetails;
import com.system.model.Product;
import com.system.model.SoldProduct;
import com.system.service.OrderDetailsService;
import com.system.service.OrderService;
import com.system.service.ProductService;
import com.system.service.SoldProductService;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsServiceImpl implements OrderDetailsService {

    private final ProductService productService = new ProductServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();
    private final SoldProductService soldProductService = new SoldProductServiceImpl();

    @Override
    public OrderDetails getById(Integer id) {
        Order order = orderService.getById(id);
        List<Product> productList = new ArrayList<>();
        List<SoldProduct> soldProductList = soldProductService.getSoldProductByOrder(order);
        Double coast = 0.0;
        for (SoldProduct soldProduct : soldProductList) {
            Product product = productService.getById(soldProduct.getProductID());
            product.setCount(soldProduct.getCount());
            productList.add(product);
            coast+=product.getPrice()*product.getCount();
        }
        return OrderDetails.builder()
                .orderId(order.getId())
                .customer(order.getCustomer())
                .productList(productList)
                .order(order)
                .coast(coast)
                .build();
    }

    @Override
    public List<OrderDetails> getAllOrderDetails() {
        List<Order> orderList = orderService.getAll();
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        for (Order order : orderList) {
            List<Product> productList = new ArrayList<>();
            List<SoldProduct> soldProductList = soldProductService.getSoldProductByOrder(order);
            Double coast = 0.0;
            for (SoldProduct soldProduct : soldProductList) {
                Product product = productService.getById(soldProduct.getProductID());
                product.setCount(soldProduct.getCount());
                productList.add(product);
                coast+=product.getPrice()*product.getCount();
            }
            orderDetailsList.add(OrderDetails.builder()
                    .orderId(order.getId())
                    .customer(order.getCustomer())
                    .productList(productList)
                    .order(order)
                    .coast(coast)
                    .build());
        }
        return orderDetailsList;
    }
}
