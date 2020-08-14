package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Agent;
import com.lambdaschool.crudyorders.models.Customer;
import com.lambdaschool.crudyorders.models.Order;
import com.lambdaschool.crudyorders.repositories.AgentsRepository;
import com.lambdaschool.crudyorders.repositories.CustomersRepository;
import com.lambdaschool.crudyorders.views.OrderCounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerServices")
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomersRepository customerrepos;



    @Autowired
    AgentsRepository agentrepo;
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
    //4


    @Override
    public List<OrderCounts> getOrderCounts() {
        List<OrderCounts> list = customerrepos.findOrderCounts();
        return list;
    }

//    private String custname;
//    private String custcity;
//    private String workingarea;
//    private String custcountry;
//    private String grade;
//    private double openingamt;
//    private double receiveamt;
//    private double paymentamt;
//    private double outstandingamt;
//    private String phone;
    // private agent
    @Transactional
    @Override
    public Customer save(Customer customer){ //post and put on the same
                                                            // TODO: figure out why order is not working
        Customer newCustomer = new Customer();

        if (customer.getCustcode() != 0)
        {
            customerrepos.findById(customer.getCustcode())
                    .orElseThrow(() -> new EntityNotFoundException("Customer " + customer.getCustcode() + " Not Found!"));

            newCustomer.setCustcode(customer.getCustcode());
        }


        //pulling from the customer constructor
        newCustomer.setCustname(customer.getCustname());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.setAgent(customer.getAgent());

        //one to many  -orders
//        this.ordamount = ordamount;
//        this.advanceamount = advanceamount;
//        this.customer= customer;
//        this.orderdescription = orderdescription;

        newCustomer.getOrders().clear();
        for(Order o : customer.getOrders()){
            Order newOrder = new Order();

            newOrder.setOrdamount(o.getOrdamount());
            newOrder.setAdvanceamount(o.getAdvanceamount());
            newOrder.setCustomer(newCustomer);
            newOrder.setOrderdescription(o.getOrderdescription());

            newCustomer.getOrders().add(newOrder);
        }

        //now setting up many to many agent
        Agent newAgent = agentrepo.findById(newCustomer.getAgent().getAgentcode())
                .orElseThrow(()-> new EntityNotFoundException("Agent " + newCustomer.getAgent().getAgentcode() + " Not Found"));
        newCustomer.setAgent(newAgent);

        return customerrepos.save(newCustomer);
    }

    //put constomer

    @Transactional
    @Override
    public Customer update(Customer customer, long id) {
        Customer currentCustomer = customerrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer " + id + " Not Found!"));


        if (customer.getCustname() != null)
        {
            currentCustomer.setCustname(customer.getCustname());
        }

        if (customer.getCustcity() != null)
        {
            currentCustomer.setCustcity(customer.getCustcity());
        }

        if (customer.getWorkingarea() != null)
        {
            currentCustomer.setWorkingarea(customer.getWorkingarea());
        }

        if (customer.getCustcountry() != null)
        {
            currentCustomer.setCustcountry(customer.getCustcountry());
        }

        if (customer.getGrade() != null)
        {
            currentCustomer.setGrade(customer.getGrade());
        }

        if (customer.getPhone() != null)
        {
            currentCustomer.setPhone(customer.getPhone());
        }

        if (customer.getAgent() != null)
        {
            currentCustomer.setAgent(customer.getAgent());
        }

        if (customer.hasvalueforopeningamt)
        {
            currentCustomer.setOpeningamt(customer.getOpeningamt());
        }

        if (customer.hasvalueforreceiveamt)
        {
            currentCustomer.setReceiveamt(customer.getReceiveamt());
        }

        if (customer.hasvalueforpaymentamt)
        {
            currentCustomer.setPaymentamt(customer.getPaymentamt());
        }

        if (customer.hasvalueforoutstandingamt)
        {
            currentCustomer.setOutstandingamt(customer.getOutstandingamt());
        }

        // one to many - orders
        if (customer.getOrders()
                .size() > 0)
        {
            currentCustomer.getOrders()
                    .clear();
            for (Order o : customer.getOrders())
            {
                Order newOrder = new Order();
                newOrder.setOrdamount(o.getOrdamount());
                newOrder.setAdvanceamount(o.getAdvanceamount());
                newOrder.setOrderdescription(o.getOrderdescription());
                newOrder.setCustomer(currentCustomer);
                currentCustomer.getOrders()
                        .add(newOrder);
            }
        }

        // many to one agent
        Agent newAgent = agentrepo.findById(currentCustomer.getAgent()
                .getAgentcode())
                .orElseThrow(() -> new EntityNotFoundException("Agent " + currentCustomer.getAgent()
                        .getAgentcode() + " Not Found"));
        currentCustomer.setAgent(newAgent);

        return customerrepos.save(currentCustomer);

    }

    //delete customer
    @Transactional
    @Override
    public void delete(long id) {
        if (customerrepos.findById(id).isPresent()) // did it find id in our list
        {
            customerrepos.deleteById(id);
        } else {
            throw new EntityNotFoundException("Customer " + id + " Not Found");
        }
    }



}
