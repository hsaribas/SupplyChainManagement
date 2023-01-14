package com.scm.service;

import com.scm.domain.Products;
import com.scm.dto.ProductsDTO;
import com.scm.exception.ResourceNotFoundException;
import com.scm.exception.message.ErrorMessage;
import com.scm.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {

    @Autowired
    ProductsRepository productsRepository;

    public ProductsDTO searchProductByName(String productsName) {
        Products products = productsRepository.findByProductName(productsName).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.PRODUCT_NOT_FOUND_MESSAGE, productsName)));

        ProductsDTO productsDTO = new ProductsDTO();
        productsDTO.setProductName(products.getProductName());
        productsDTO.setQuantity(products.getQuantity());
        productsDTO.setPrice(products.getPrice());

        return productsDTO;
    }

    public void addNewProduct(ProductsDTO productsDTO) {
        List<Products> productsFound = productsRepository.findAll();
        for (Products products : productsFound) {
            if (products.getProductName().equals(productsDTO.getProductName())) {
                throw new ResourceNotFoundException(String.format(ErrorMessage.PRODUCT_ALREADY_EXIST_MESSAGE, products.getProductName()));
            }
        }

        Products products = new Products();
        products.setProductName(productsDTO.getProductName());
        products.setPrice(productsDTO.getPrice());
        products.setQuantity(productsDTO.getQuantity());

        productsRepository.save(products);
    }

    public Products getProductById(Long id) {
        return productsRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.PRODUCT_NOT_FOUND_MESSAGE, id)));
    }

    public List<Products> getAllProducts() {

        return productsRepository.findAll();
    }

    public void saveProduct(Products products) {

        productsRepository.save(products);
    }
}
