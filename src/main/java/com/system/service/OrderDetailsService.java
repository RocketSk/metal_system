package com.system.service;

import com.system.model.OrderDetails;

import java.util.List;

public interface OrderDetailsService {

    OrderDetails getById(Integer id);

    List<OrderDetails> getAllOrderDetails();

}
