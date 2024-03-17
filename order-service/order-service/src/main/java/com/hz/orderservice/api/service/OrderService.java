package com.hz.orderservice.api.service;


import com.hz.orderservice.api.common.Payment;
import com.hz.orderservice.api.common.TransactionRequest;
import com.hz.orderservice.api.common.TransactionResponse;
import com.hz.orderservice.api.entity.Order;
import com.hz.orderservice.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RefreshScope
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    @Lazy
    private RestTemplate template;

    @Value("${microservice.payment-service.endpoints.endpoint.uri}")
    private String ENDPOINT_URL;


    public TransactionResponse saveOrder(TransactionRequest transactionRequest){
        String response = "";
        Order order = transactionRequest.getOrder();
        Order savedOrder = orderRepository.save(order);
        Payment payment = transactionRequest.getPayment();
        payment.setOrderId(savedOrder.getId());
        payment.setAmount(savedOrder.getPrice());

        // rest call
        Payment paymentResponse = template.postForObject(ENDPOINT_URL, payment, Payment.class);
        response = paymentResponse.getPaymentStatus().equals("success") ? "payment processing successful and order placed" : "there is a failure in payment api, order added to cart";
        return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(), response);

    }
}
