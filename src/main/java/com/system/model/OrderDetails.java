package com.system.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {

    private Integer orderId;
    private Order order;
    private Customer customer;
    private List<Product> productList;
    private Double coast;

}
