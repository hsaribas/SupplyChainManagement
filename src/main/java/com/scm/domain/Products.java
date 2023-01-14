package com.scm.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data

@Table(name = "tbl_products")
@Entity
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private BigDecimal price;
}
