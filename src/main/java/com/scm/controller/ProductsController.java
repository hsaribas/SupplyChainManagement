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

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    ProductsService productsService;

    //http://localhost:8080/products/get
    @PreAuthorize("hasRole('RETAILER')")
    @GetMapping("/get")
    public ResponseEntity<ProductsDTO> searchProduct(@RequestParam("productName") String productsName) {
        ProductsDTO productsDTO = productsService.searchProductByName(productsName);
        return ResponseEntity.ok(productsDTO);
    }

    //http://localhost:8080/products/{id}
    @PreAuthorize("hasRole('RETAILER')")
    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable("id") Long id) {
        Products products = productsService.getProductById(id);
        return ResponseEntity.ok(products);
    }

    //http://localhost:8080/products/{imageId}/add
    @PreAuthorize("hasRole('RETAILER')")
    @PostMapping("/{imageId}/add")
    public ResponseEntity<SCMResponse> saveNewProduct(@PathVariable String imageId, @Valid @RequestBody ProductsDTO productsDTO) {
        productsService.addNewProduct(imageId, productsDTO);
        SCMResponse response = new SCMResponse(ResponseMessage.PRODUCT_SAVED_RESPONSE_MESSAGE, true);
        return ResponseEntity.ok(response);
    }
}
