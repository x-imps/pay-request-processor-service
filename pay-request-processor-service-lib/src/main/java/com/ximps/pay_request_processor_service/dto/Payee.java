package com.ximps.pay_request_processor_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payee implements Serializable {

    private String bankName;

    @NotNull
    private String ifsc;

    @NotNull
    private String accountNumber;

    @NotNull
    private String accountType;
}
