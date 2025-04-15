package com.ximps.pay_request_processor_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payer implements Serializable {

    @NotNull
    private String customerId;

    @NotNull
    private String accountId;
}
