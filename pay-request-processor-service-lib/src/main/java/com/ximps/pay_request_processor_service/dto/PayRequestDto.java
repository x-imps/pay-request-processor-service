package com.ximps.pay_request_processor_service.dto;

import com.ximps.pay_request_processor_service.constants.PaymentStatus;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PayRequestDto implements Serializable {

    public String id;

    public String transactionId;

    public String payerCustomerId;

    public String payerAccountId;

    public String payeeBank;

    public String payeeIFSC;

    public String payeeAccountType;

    public String payeeAccountNumber;

    public Double amount;

    public String notes;

    public PaymentStatus status;

    public String failedReason;

    public LocalDateTime createdOn;

}