package com.payment.paymentservice.services;

import com.payment.paymentservice.paymentgateway.PaymentGateway;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private PaymentGateway paymentGateway;

    public PaymentService(PaymentGateway paymentGateway){
        this.paymentGateway = paymentGateway;
    }

    public String generatePaymentLink(Long orderId, Long amount) throws RazorpayException, StripeException {
        return paymentGateway.generatePaymentLink(orderId, amount);
    }

}
