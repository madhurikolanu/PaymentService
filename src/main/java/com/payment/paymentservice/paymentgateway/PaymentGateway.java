package com.payment.paymentservice.paymentgateway;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentGateway {

    public String generatePaymentLink(Long orderId, Long amount) throws RazorpayException, StripeException;
}
