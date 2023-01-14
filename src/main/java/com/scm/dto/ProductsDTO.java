package com.scm.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductsDTO {

    private String productName;

    private int quantity;

    private BigDecimal price;
}
