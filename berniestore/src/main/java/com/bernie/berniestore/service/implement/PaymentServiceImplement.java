package com.bernie.berniestore.service.implement;

import com.bernie.berniestore.dto.PaymentIntentRequestDTO;
import com.bernie.berniestore.dto.PaymentIntentResponseDTO;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImplement implements IPaymentService {

    @Override
    public PaymentIntentResponseDTO createPaymentIntent(PaymentIntentRequestDTO requestDto) {
        try {
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount(requestDto.amount())
                    .setCurrency(requestDto.currency())
                    .addPaymentMethodType("card")
                    .build();
            PaymentIntent paymentIntent = PaymentIntent.create(params);
            return new PaymentIntentResponseDTO(paymentIntent.getClientSecret());
        } catch (StripeException stripeException) {
            throw new RuntimeException("Failed to create payment intent", stripeException);
        }
    }
}
