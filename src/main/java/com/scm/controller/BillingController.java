package com.scm.controller;

import com.scm.domain.Billing;
import com.scm.dto.BillingDTO;
import com.scm.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/billing")
@PreAuthorize("hasRole('RETAILER')")
public class BillingController {

    @Autowired
    private BillingService billingService;

    //http://localhost:8080/billing/generate
    @PostMapping("/generate")
    public ResponseEntity<Billing> generateBilling(@RequestBody BillingDTO billingDTO) {
        Billing billing = billingService.generateBilling(billingDTO);

        return ResponseEntity.ok(billing);
    }
}
