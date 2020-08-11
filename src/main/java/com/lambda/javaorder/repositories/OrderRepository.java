package com.lambda.javaorder.repositories;

import com.lambda.javaorder.models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
