package com.payment.paymentservice.paymentgateway.impl;

import com.payment.paymentservice.paymentgateway.PaymentGateway;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class StripePaymentGateway implements PaymentGateway {
    @Override
    public String generatePaymentLink(Long orderId, Long amount) throws StripeException {
       // similar to razorpay get the id from stripe test account and add here
        Stripe.apiKey = "sk_test_tR3PYbcVNZZ796tH88S4VQ2u";

        PriceCreateParams priceParams =
                PriceCreateParams.builder()
                        .setCurrency("INR")
                        .setUnitAmount(10000L)
                        .setProductData(PriceCreateParams.ProductData.builder().setName("IPHONE").build())
                        .build();

        Price price = Price.create(priceParams);
        PaymentLinkCreateParams params =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId().toString())
                                        .setQuantity(1L)
                                        .build()
                        )
                        // to redirect url
                        .setAfterCompletion(PaymentLinkCreateParams.AfterCompletion.builder()
                                .setRedirect(
                                        PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                .setUrl("https://scaler.com")
                                                .build()
                                )
                                .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                .build()
                        )
                        .build();

        // to setup webhook url create a public url of your application and in stripe application go to developers and create webhook url
        // you can use localtunnel(work around for time being) to dpeloy your local application as it takes to dpeloy multiple times in AWS
        // so provide public url and provide other details for webhook url configuration

        PaymentLink paymentLink = PaymentLink.create(params);
        return paymentLink.toString();
    }
}
