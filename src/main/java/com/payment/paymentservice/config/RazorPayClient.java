package com.payment.paymentservice.config;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorPayClient {
    // set the test mode client id and secret from razorpay application under environment variables
    @Value("${razor.pay.clientid}")
    private String clientId;
    @Value("${razor.pay.secret}")
    private String secret;

    @Bean
    public RazorpayClient getRazorPayClient() throws RazorpayException {
        return new RazorpayClient(clientId, secret);
    }
}
