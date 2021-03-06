package com.lambdaschool.crudyorders.controllers;

import com.lambdaschool.crudyorders.models.Customer;
import com.lambdaschool.crudyorders.services.CustomerService;
import com.lambdaschool.crudyorders.views.OrderCounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerServices;

    // http://localhost:2019/customers/orders
    @GetMapping(value = "orders", produces = "application/json")
    public ResponseEntity<?> listAllCustomers(){
        List<Customer> myList= customerServices.listAllCustomers();
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }

    //http://localhost:2019/customers/customer/7

    @GetMapping(value = "/customer/{custcode}",produces = "application/json")
    public ResponseEntity<?> findCustomerById(@PathVariable long custcode){
        Customer myCustomer = customerServices.findCustomersByCode(custcode);
        return new ResponseEntity<>(myCustomer,HttpStatus.OK);
    }

    //http://localhost:2019/customers/namelike/mes
    @GetMapping(value = "/namelike/{custname}", produces = "application/json")
    public ResponseEntity<?> findCustomerByNameLike(@PathVariable String custname){
        List<Customer> myList = customerServices.findAllCustomersByNameLike(custname);
        return new ResponseEntity<>(myList,HttpStatus.OK);
    }

    // http://localhost:2019/customers/orders/count - Using a custom query
    @GetMapping(value = "/orders/count", produces = "application/json")
    public ResponseEntity<?> getOrderCount()
    {
        List<OrderCounts> myList = customerServices.getOrderCounts();
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }

    //todays project



    //POST http://localhost:2019/customers/customer
    @PostMapping(value = "/customer", consumes = "application/json")
    public ResponseEntity<?> addNewCustomer(@Valid @RequestBody Customer newCustomer){
        newCustomer.setCustcode(0); //is the same as null
        newCustomer = customerServices.save(newCustomer);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/" + newCustomer.getCustcode())
                .build()
                .toUri();
        responseHeaders.setLocation(newCustomerURI);
        return new ResponseEntity<>(null,responseHeaders,HttpStatus.CREATED);
    }



    //PUT http://localhost:2019/customers/customer/19
    @PutMapping(value = "/customer/{custcode}", consumes ="application/json")
    public ResponseEntity<?> updateFullCustomer(@RequestBody Customer updateCustomer, @PathVariable long custcode){
        updateCustomer.setCustcode(custcode);
        customerServices.save(updateCustomer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //patch
    @PatchMapping(value = "/customer/{custcode}", consumes = "application/json")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer updateCustomer,
                                            @PathVariable long custcode)
    {
        customerServices.update(updateCustomer, custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // DELETE http://localhost:2019/customers/customer/{custcode} - Deletes the given customer including any associated orders
    @DeleteMapping(value = "customer/{custcode}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable long custcode){
        customerServices.delete(custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
