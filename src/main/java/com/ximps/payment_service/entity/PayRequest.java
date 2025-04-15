package com.ximps.payment_service.entity;

import com.ximps.pay_request_processor_service.constants.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class PayRequest {

    @Id
    public String id;

    public String transactionId;

    public String payerCustomerId;

    public String payerAccountId;

    public String payeeBank;

    public String payeeIfsc;

    public String payeeAccountType;

    public String payeeAccountNumber;

    public Double amount;

    public String notes;

    @Enumerated(EnumType.STRING)
    public PaymentStatus status;

    public String failedReason;

    @CreatedDate
    public LocalDateTime createdOn;
}
