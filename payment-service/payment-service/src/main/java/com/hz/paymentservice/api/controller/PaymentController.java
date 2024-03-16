package com.hz.paymentservice.api.controller;

import com.hz.paymentservice.api.entity.Payment;
import com.hz.paymentservice.api.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("do-payment")
    public Payment doPayment(@RequestBody Payment payment){
        return paymentService.doPayment(payment);
    }

    @GetMapping("/{orderId}")
    public Payment paymentHistoryByOrderId(@PathVariable int orderId){
        return paymentService.findPaymentHistoryByOrderId(orderId);
    }

}
