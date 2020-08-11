package com.lambda.javaorder.repositories;

import com.lambda.javaorder.models.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
