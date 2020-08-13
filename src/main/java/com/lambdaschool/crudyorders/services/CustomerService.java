package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Customer;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CustomerService {

    // http://localhost:2019/customers/orders
    List<Customer> listAllCustomers();

    //http://localhost:2019/customers/customer/7
    Customer findCustomersByCode(long id);

    //http://localhost:2019/customers/namelike/mes
    List<Customer> findAllCustomersByNameLike(String custn);

    Customer save(Customer customer);
}
