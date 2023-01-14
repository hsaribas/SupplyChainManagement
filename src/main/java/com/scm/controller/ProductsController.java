package com.scm.controller;

import com.scm.domain.Products;
import com.scm.dto.ProductsDTO;
import com.scm.dto.response.ResponseMessage;
import com.scm.dto.response.SCMResponse;
import com.scm.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    ProductsService productsService;

    @PreAuthorize("hasRole('RETAILER')")
    @GetMapping("/getProduct")
    public ResponseEntity<ProductsDTO> searchProduct(@RequestParam("productName") String productsName) {
        ProductsDTO productsDTO = productsService.searchProductByName(productsName);
        return ResponseEntity.ok(productsDTO);
    }

    @PreAuthorize("hasRole('RETAILER')")
    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable("id") Long id) {
        Products products = productsService.getProductById(id);
        return ResponseEntity.ok(products);
    }

    @PreAuthorize("hasRole('RETAILER')")
    @PostMapping("/addProduct")
    public ResponseEntity<SCMResponse> saveNewProduct(@RequestBody ProductsDTO productsDTO) {
        productsService.addNewProduct(productsDTO);
        SCMResponse response = new SCMResponse(ResponseMessage.PRODUCT_SAVED_RESPONSE_MESSAGE, true);
        return ResponseEntity.ok(response);
    }
}
