package com.scm.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductsDTO {

    private String productName;

    private int quantity;

    private BigDecimal price;

    private Set<String> image;
}
