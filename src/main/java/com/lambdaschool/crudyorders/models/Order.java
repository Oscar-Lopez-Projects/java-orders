package com.lambdaschool.crudyorders.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long ordnum;

    @Transient
    public boolean hasvalueforordamount;
    private double ordamount;

    @Transient
    public boolean hasvalueforadvanceamount;
    private double advanceamount;

    private String orderdescription;

    //many to many join tables

    @ManyToOne
    @JoinColumn(name = "custcode", nullable = false)
    @JsonIgnoreProperties("orders")
    private Customer customer;

    @ManyToMany
    @JoinTable(name = "orderspayments",
            joinColumns = @JoinColumn(name = "ordnum"),
            inverseJoinColumns = @JoinColumn(name = "paymentid"))
    @JsonIgnoreProperties("orders")
    private Set<Payment> payments = new HashSet<>();

    public Order() {
    }

    //constructor
    public Order(double ordamount, double advanceamount, Customer customer,String orderdescription) {
        this.ordamount = ordamount;
        this.advanceamount = advanceamount;
        this.customer= customer;
        this.orderdescription = orderdescription;

    }
    //setters and getters for foreign key


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    public void addPayments(Payment payment){
        this.payments.add(payment);
    }
    //setters and getters
    public long getOrdnum() {
        return ordnum;
    }

    public void setOrdnum(long ordnum) {
        this.ordnum = ordnum;
    }

    public double getOrdamount() {
        return ordamount;
    }

    public void setOrdamount(double ordmount) {
        this.ordamount = ordmount;
    }

    public double getAdvanceamount() {
        return advanceamount;
    }

    public void setAdvanceamount(double advanceamount) {
        this.advanceamount = advanceamount;
    }

    public String getOrderdescription() {
        return orderdescription;
    }

    public void setOrderdescription(String orderdescription) {
        this.orderdescription = orderdescription;
    }
}
