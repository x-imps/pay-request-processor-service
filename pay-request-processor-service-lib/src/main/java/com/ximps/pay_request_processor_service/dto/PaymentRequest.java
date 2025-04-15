package com.ximps.pay_request_processor_service.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest implements Serializable {

    @NotNull
    private String transactionId;

    @NotNull
    @Valid
    private Payer payer;

    @NotNull
    @Valid
    private Payee payee;

    @NotNull
    @Positive(message = "amount must be positive")
    private Double amount;

    private String note;
}
