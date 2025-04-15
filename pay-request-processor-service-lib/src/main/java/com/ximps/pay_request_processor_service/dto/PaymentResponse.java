package com.ximps.pay_request_processor_service.dto;

import com.ximps.pay_request_processor_service.constants.PaymentStatus;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse implements Serializable {
    public String paymentId;
    public String transactionId;
    public PaymentStatus paymentStatus;
    public String failedReason;
}
