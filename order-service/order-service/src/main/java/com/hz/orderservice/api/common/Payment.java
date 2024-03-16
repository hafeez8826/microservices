package com.hz.orderservice.api.common;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {


    private int paymentId;
    private String paymentStatus;
    private String transactionId;

    private int orderId;
    private double amount;
}
