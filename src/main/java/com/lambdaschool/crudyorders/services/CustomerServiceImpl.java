package com.lambda.javaorder.services;

import com.lambda.javaorder.models.Customer;
import com.lambda.javaorder.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerrepos;

    @Override
    public Customer save(Customer customer){
        return customerrepos.save(customer);
    }
}
