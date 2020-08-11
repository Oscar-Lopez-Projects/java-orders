package com.lambda.javaorder.repositories;

import com.lambda.javaorder.models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
