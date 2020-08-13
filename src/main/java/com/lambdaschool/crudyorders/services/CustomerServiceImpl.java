package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Customer;
import com.lambdaschool.crudyorders.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerServices")
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomersRepository customerrepos;
    //1
    @Override
    public List<Customer> listAllCustomers() {
        List<Customer> list = new ArrayList<>();
        customerrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }
    //2


    @Override
    public Customer findCustomersByCode(long id) {
        return customerrepos.findById(id)
                .orElseThrow(()->new EntityNotFoundException("customer "+ id + " Not found"));
    }
    //3

    @Override
    public List<Customer> findAllCustomersByNameLike(String custn) {
        return customerrepos.findByCustnameContainingIgnoringCase(custn);
    }

    @Override
    public Customer save(Customer customer){
        return customerrepos.save(customer);
    }
}
