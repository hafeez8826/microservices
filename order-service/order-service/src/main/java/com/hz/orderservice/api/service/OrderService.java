package com.hz.orderservice.api.service;


import com.hz.orderservice.api.common.Payment;
import com.hz.orderservice.api.common.TransactionRequest;
import com.hz.orderservice.api.common.TransactionResponse;
import com.hz.orderservice.api.entity.Order;
import com.hz.orderservice.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate template;

    public TransactionResponse saveOrder(TransactionRequest transactionRequest){
        String response = "";
        Order order = transactionRequest.getOrder();
        Order savedOrder = orderRepository.save(order);
        Payment payment = transactionRequest.getPayment();
        payment.setOrderId(savedOrder.getId());
        payment.setAmount(savedOrder.getPrice());

        // rest call
        Payment paymentResponse = template.postForObject("http://PAYMENT-SERVICE/payment/do-payment", payment, Payment.class);
        response = paymentResponse.getPaymentStatus().equals("success") ? "payment processing successful and order placed" : "there is a failure in payment api, order added to cart";
        return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(), response);

    }
}
