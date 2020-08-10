package com.lambda.javaorder.models;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ordnum;

    @Column(unique = true,nullable = false)
    private long custcode;

    private double ordmount;
    private double advanceamount;
    private String orderdescription;

    public Order() {
    }

    //constructor
    public Order(long custcode, double ordmount, double advanceamount, String orderdescription) {
        this.custcode = custcode;
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

    public long getCustcode() {
        return custcode;
    }

    public void setCustcode(long custcode) {
        this.custcode = custcode;
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
