package com.scm.service;

import com.scm.domain.Billing;
import com.scm.domain.Products;
import com.scm.dto.BillingDTO;
import com.scm.repository.BillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BillingService {

    @Autowired
    private BillingRepository billingRepository;

    @Autowired
    private ProductsService productsService;

    public Billing generateBilling(BillingDTO billingDTO) {
        List<Products> productsList = productsService.getAllProducts();

        Billing billing = new Billing();
        productsList.forEach(product -> {
            if (product.getProductName().equals(billingDTO.getProductName())) {
                billing.setProduct(product);
                billing.setProductName(billingDTO.getProductName());
                billing.setTotalAmount(product.getPrice().multiply(BigDecimal.valueOf(billingDTO.getProductQuantity())));
                billing.setProductPrice(product.getPrice());
                billing.setProductQuantity(billingDTO.getProductQuantity());
                product.setQuantity(product.getQuantity() - billingDTO.getProductQuantity());

                productsService.saveProduct(product);
            }
        });
        billingRepository.save(billing);

        return billing;
    }
}
