package com.ximps.payment_service.clients;

import com.ximps.cbs_simulation_service.dto.CreditRequest;
import com.ximps.cbs_simulation_service.dto.CreditResponse;
import com.ximps.cbs_simulation_service.dto.DebitRequest;
import com.ximps.cbs_simulation_service.dto.DebitResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "cbs-simulation-service", url = "http://localhost:8080/cbs-simulation-service")
public interface CBSSimulationService {

    @PostMapping("/account/debit")
    public ResponseEntity<DebitResponse> debit(@RequestBody @Valid DebitRequest debitRequest);

    @PostMapping("/account/credit")
    public ResponseEntity<CreditResponse> credit(@RequestBody CreditRequest creditRequest);
}
