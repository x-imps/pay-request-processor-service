package com.ximps.payment_service.controller;

import com.ximps.pay_request_processor_service.dto.PayRequestDto;
import com.ximps.pay_request_processor_service.dto.PaymentRequest;
import com.ximps.pay_request_processor_service.dto.PaymentResponse;
import com.ximps.payment_service.entity.PayRequest;
import com.ximps.payment_service.mapper.PayRequestMapper;
import com.ximps.payment_service.service.PayRequestService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping
@Slf4j
public class PayRequestProcessorController {

    @Autowired
    private PayRequestService payRequestService;

    @Autowired
    private PayRequestMapper paymentMapper;

    @GetMapping("/{payRequestId}")
    public PayRequestDto findById(@PathVariable String payRequestId) {
        PayRequest payment = payRequestService.find(payRequestId);
        return paymentMapper.entityToDto(payment);
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> startPayment(@RequestBody @Valid PaymentRequest paymentRequest) {

        log.info("Processing payment: "+paymentRequest.getTransactionId());

        PayRequest payment = PayRequest.builder()
                .id(UUID.randomUUID().toString())
                .transactionId(paymentRequest.getTransactionId())
                .amount(paymentRequest.getAmount())
                .payerCustomerId(paymentRequest.getPayer().getCustomerId())
                .payerAccountId(paymentRequest.getPayer().getAccountId())
                .payeeBank(paymentRequest.getPayee().getBankName())
                .payeeIfsc(paymentRequest.getPayee().getIfsc())
                .payeeAccountNumber(paymentRequest.getPayee().getAccountNumber())
                .payeeAccountType(paymentRequest.getPayee().getAccountType())
                .notes(paymentRequest.getNote())
                .build();

        payment = payRequestService.processPayRequest(payment);

        PaymentResponse paymentResponse = PaymentResponse.builder().paymentId(payment.getId())
                .transactionId(payment.getTransactionId())
                .paymentStatus(payment.getStatus())
                .failedReason(payment.getFailedReason())
                .build();

        return  ResponseEntity.ok(paymentResponse);
    }

}
