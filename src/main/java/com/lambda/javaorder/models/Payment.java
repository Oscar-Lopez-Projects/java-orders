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

    //connect tables

    public Payment() {
    }
    //Where is this coming from??????
    public Payment(String type){
        this.type=type;
    }
    //End of ^
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
