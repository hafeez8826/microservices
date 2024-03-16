package com.hz.paymentservice.api.repository;

import com.hz.paymentservice.api.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Payment findByOrderId(int orderId);
}
