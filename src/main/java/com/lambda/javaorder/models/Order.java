package com.lambda.javaorder.models;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long ordnum;

    private double ordmount;
    private double advanceamount;
    private String orderdescription;

    public Order() {
    }

    //constructor
    public Order(double ordmount, double advanceamount, String orderdescription) {
        this.ordmount = ordmount;
        this.advanceamount = advanceamount;
        this.orderdescription = orderdescription;
    }

    //setters and getters
    public long getOrdnum() {
        return ordnum;
    }

    public void setOrdnum(long ordnum) {
        this.ordnum = ordnum;
    }

    public double getOrdmount() {
        return ordmount;
    }

    public void setOrdmount(double ordmount) {
        this.ordmount = ordmount;
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
