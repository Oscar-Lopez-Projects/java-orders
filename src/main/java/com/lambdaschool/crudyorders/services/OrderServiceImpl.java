package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Customer;
import com.lambdaschool.crudyorders.models.Order;
import com.lambdaschool.crudyorders.models.Payment;
import com.lambdaschool.crudyorders.repositories.CustomersRepository;
import com.lambdaschool.crudyorders.repositories.OrdersRepository;
import com.lambdaschool.crudyorders.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service(value = "orderServices")
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrdersRepository orderrepos;

    @Autowired
    CustomersRepository customerrepos;

    @Autowired
    PaymentRepository paymentrepos;
    @Override
    public Order findByOrderNum(long id) {
        return orderrepos.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Order " + id + " Not found"));
    }

    @Transactional
    @Override
    public Order update(Order order, long id) {
        Order currentOrder = orderrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order " + id + " Not Found!"));

        if (order.getOrderdescription() != null)
        {
            currentOrder.setOrderdescription(order.getOrderdescription());
        }

        // make changes in order model then..
        //fixme Seat Capacity
        if (order.hasvalueforadvanceamount)
        {
            currentOrder.setAdvanceamount(order.getAdvanceamount());
        }

        if (order.hasvalueforordamount)
        {
            currentOrder.setOrdamount(order.getOrdamount());
        }

        // many to many
        if (order.getPayments().size() > 0)
        {
            currentOrder.getPayments().clear();
            for (Payment p : order.getPayments())
            {
                Payment newPay = paymentrepos.findById(p.getPaymentid()) // find payment that matches in DB
                        .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " Not Found!"));

                currentOrder.getPayments()
                        .add(newPay);
            }
        }
        return orderrepos.save(currentOrder);
    }
    @Transactional
    @Override
    public void delete(long id) {
        if (orderrepos.findById(id).isPresent())
        {
            orderrepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException("Order " + id + " Not Found!");
        }

    }
    @Transactional
    @Override
    public Order save(Order order){
        Order newOrder = new Order();

        if (order.getOrdnum() != 0)
        {
            orderrepos.findById(order.getOrdnum())
                    .orElseThrow(() -> new EntityNotFoundException("Order " + order.getOrdnum() + " Not Found!"));


        }
        newOrder.setOrdnum(order.getOrdnum());
        newOrder.setOrderdescription(order.getOrderdescription());
        newOrder.setAdvanceamount(order.getAdvanceamount());
        newOrder.setOrdamount(order.getOrdamount());
        newOrder.setCustomer(customerrepos.findById(order.getCustomer().getCustcode())
              .orElseThrow(()-> new EntityNotFoundException("Customer " + order.getCustomer().getCustcode() +" not found")));




        newOrder.getPayments().clear();

        for (Payment p : order.getPayments())
        {
            Payment newPay = paymentrepos.findById(p.getPaymentid()) // find payment that matches in DB
                    .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " Not Found!"));

            newOrder.getPayments().add(newPay);
        }

        return orderrepos.save(newOrder);
    }


}
