package com.scm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateRequest {

    @NotNull(message = "Please provide a product name")
    private String productName;

    @NotNull(message = "Please provide quantity")
    private int quantity;
}
