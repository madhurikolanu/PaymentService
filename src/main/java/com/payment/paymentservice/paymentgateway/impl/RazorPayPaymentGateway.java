package com.payment.paymentservice.paymentgateway.impl;

import com.payment.paymentservice.config.RazorPayClient;
import com.payment.paymentservice.paymentgateway.PaymentGateway;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.json.JSONObject;

@Component
//@Primary
public class RazorPayPaymentGateway implements PaymentGateway {

    private RazorPayClient razorPayClient;
    public RazorPayPaymentGateway(RazorPayClient razorPayClient){
        this.razorPayClient = razorPayClient;
    }

    @Override
    public String generatePaymentLink(Long orderId, Long amount) throws RazorpayException {
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",amount);
        paymentLinkRequest.put("currency","INR");
        // paymentLinkRequest.put("accept_partial",true);
        //  paymentLinkRequest.put("first_min_partial_amount",100);
        paymentLinkRequest.put("expire_by",1691097057);
        paymentLinkRequest.put("reference_id",orderId);
        paymentLinkRequest.put("description","Payment for policy no #23456");
        JSONObject customer = new JSONObject();
        customer.put("name","+919000090000");
        customer.put("contact","Madhuri Kolanu");
        customer.put("email","madhuri.kolanu@scaler.com");
        paymentLinkRequest.put("customer",customer);
        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("notify",notify);
        paymentLinkRequest.put("reminder_enable",true);
        JSONObject notes = new JSONObject();
        // notes.put("policy_name","Jeevan Bima");
        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://www.scaler.com/academy/mentee-dashboard/class/198372/session?joinSession=1");
        paymentLinkRequest.put("callback_method","get");


        PaymentLink payment = razorPayClient.getRazorPayClient().paymentLink.create(paymentLinkRequest);

        return payment.toString();
    }
}
