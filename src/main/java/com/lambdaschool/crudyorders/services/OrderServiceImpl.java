package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Order;
import com.lambdaschool.crudyorders.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceImpl implements OrderService{
    @Autowired
    OrdersRepository orderrepos;

    @Override
    public Order save(Order order){
        return orderrepos.save(order);
    }
}
