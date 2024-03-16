package com.hz.orderservice.api.controller;

import com.hz.orderservice.api.common.Payment;
import com.hz.orderservice.api.common.TransactionRequest;
import com.hz.orderservice.api.common.TransactionResponse;
import com.hz.orderservice.api.entity.Order;
import com.hz.orderservice.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.ObjDoubleConsumer;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/book-order")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest transactionRequest){
        return orderService.saveOrder(transactionRequest);
        // do a rest call to payment-service and pass the order id
    }

}
