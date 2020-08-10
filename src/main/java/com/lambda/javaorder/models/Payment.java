package com.lambda.javaorder.models;

import javax.persistence.*;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long paymentid;

    @Column(unique = true,nullable = false)
    private String type;

    //constructor of type


    public Payment(String type) {
        this.type = type;
    }

    public Payment() {
    }
    //getter and setters

    public long getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(long paymentid) {
        this.paymentid = paymentid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
