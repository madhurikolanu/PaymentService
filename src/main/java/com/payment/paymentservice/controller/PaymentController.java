package com.payment.paymentservice.controller;

import com.payment.paymentservice.dto.PaymentRequestDto;
import com.payment.paymentservice.services.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private PaymentService paymentService;

    PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping("/")
    public String initiatePayment(@RequestBody PaymentRequestDto paymentRequestDto){
        try {
            return paymentService.generatePaymentLink(paymentRequestDto.getOrderId(),
                    paymentRequestDto.getAmount());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
