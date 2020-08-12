package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Customer;

import java.util.List;

public interface CustomerService {

    // http://localhost:2019/customers/orders
    List<Customer> listAllCustomers();


    Customer save(Customer customer);
}
