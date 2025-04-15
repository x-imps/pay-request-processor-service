package com.ximps.payment_service.service;

import com.ximps.cbs_simulation_service.dto.CreditRequest;
import com.ximps.cbs_simulation_service.dto.CreditResponse;
import com.ximps.common.service.AbstractService;
import com.ximps.common.service.KafkaProducerService;
import com.ximps.pay_request_processor_service.constants.PaymentStatus;
import com.ximps.payment_service.clients.CBSSimulationService;
import com.ximps.payment_service.entity.PayRequest;
import com.ximps.payment_service.repository.PayRequestRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@Transactional
@Slf4j
public class PayRequestService extends AbstractService<PayRequest, String, PayRequestRepository> {

    @Autowired
    private CBSSimulationService cbsSimulationService;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    public PayRequest processPayRequest(PayRequest payment) {

        //perform credit in cbs
        if(Objects.isNull(payment.getStatus()) || !payment.getStatus().equals(PaymentStatus.FAILED)) {
            CreditRequest creditRequest = CreditRequest.builder()
                    .transactionId(payment.getTransactionId())
                    .accountId(payment.getPayerCustomerId())
                    .amount(payment.getAmount())
                    .note(payment.getNotes())
                            .build();
            ResponseEntity<CreditResponse> cbsResponse = cbsSimulationService.credit(creditRequest);
            if(cbsResponse.getStatusCode().isSameCodeAs(HttpStatus.OK)) {
                CreditResponse creditResponse = cbsResponse.getBody();
                assert creditResponse != null;
                if(!creditResponse.getStatus().equals("SUCCESS")) {
                    payment.setStatus(PaymentStatus.FAILED);
                    payment.setFailedReason("Error while performing Credit in CBS");
                }
            }
        }

        if(Objects.isNull(payment.getStatus())) {
            payment.setStatus(PaymentStatus.SUCCESS);
        }

        this.save(payment);
        kafkaProducerService.publish("pay-request", payment);

        return payment;
    }
}
