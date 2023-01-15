package com.scm.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.math3.stat.descriptive.summary.Product;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "tbl_billing")
@Entity
public class Billing extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal totalAmount;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private BigDecimal productPrice;

    @Column(nullable = false)
    private int productQuantity;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Products product;
}
