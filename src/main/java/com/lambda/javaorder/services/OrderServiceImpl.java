package com.lambda.javaorder.services;

import com.lambda.javaorder.models.Order;
import com.lambda.javaorder.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderrepos;

    @Override
    public Order save(Order order){
        return orderrepos.save(order);
    }
}
