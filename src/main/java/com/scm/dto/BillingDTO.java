package com.scm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillingDTO {

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private int productQuantity;
}
