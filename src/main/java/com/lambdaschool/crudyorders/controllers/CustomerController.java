package com.lambdaschool.crudyorders.controllers;

import com.lambdaschool.crudyorders.models.Customer;
import com.lambdaschool.crudyorders.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerServices;

    // // http://localhost:2019/customers/orders
    @GetMapping(value = "orders", produces = "application/json")
    public ResponseEntity<?> listAllCustomers(){
        List<Customer> myList= customerServices.listAllCustomers();
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }


}
