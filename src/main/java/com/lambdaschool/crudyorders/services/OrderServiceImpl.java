package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Order;
import com.lambdaschool.crudyorders.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service(value = "orderServices")
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrdersRepository orderrepos;

    @Override
    public Order findByOrderNum(long id) {
        return orderrepos.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Order " + id + " Not found"));
    }

    @Override
    public Order save(Order order){
        return orderrepos.save(order);
    }
}
