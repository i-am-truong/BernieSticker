package com.bernie.berniestore.service.implement;

import com.bernie.berniestore.dto.PaymentIntentRequestDTO;
import com.bernie.berniestore.dto.PaymentIntentResponseDTO;

public interface IPaymentService {
    PaymentIntentResponseDTO createPaymentIntent(PaymentIntentRequestDTO requestDto);
}
