package com.system.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderDetails {

    private Integer orderId;
    private Order order;
    private Customer customer;
    private List<Product> productList;
    private Double coast;

}
