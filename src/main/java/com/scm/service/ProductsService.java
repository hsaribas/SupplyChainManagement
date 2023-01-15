package com.scm.service;

import com.scm.domain.ImageFile;
import com.scm.domain.Products;
import com.scm.dto.ProductsDTO;
import com.scm.exception.ResourceNotFoundException;
import com.scm.exception.message.ErrorMessage;
import com.scm.mapper.ProductsMapper;
import com.scm.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ProductsMapper productsMapper;

    @Autowired
    private ImageFileService imageFileService;

    public ProductsDTO searchProductByName(String productsName) {
        Products products = productsRepository.findByProductName(productsName).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.PRODUCT_NOT_FOUND_MESSAGE, productsName)));

        ProductsDTO productsDTO = new ProductsDTO();
        productsDTO.setProductName(products.getProductName());
        productsDTO.setQuantity(products.getQuantity());
        productsDTO.setPrice(products.getPrice());

        return productsDTO;
    }

    public void addNewProduct(String ImageId, ProductsDTO productsDTO) {
        ImageFile imageFile = imageFileService.findImageById(ImageId);
        List<Products> productsFound = productsRepository.findAll();

        for (Products products : productsFound) {
            if (products.getProductName().equals(productsDTO.getProductName())) {
                throw new ResourceNotFoundException(String.format(ErrorMessage.PRODUCT_ALREADY_EXIST_MESSAGE, products.getProductName()));
            }
        }

        Products products = productsMapper.productsDTOToProducts(productsDTO);
        products.setProductName(productsDTO.getProductName());
        products.setPrice(productsDTO.getPrice());
        products.setQuantity(productsDTO.getQuantity());

        Set<ImageFile> imFiles = new HashSet<>();
        imFiles.add(imageFile);
        products.setImage(imFiles);

        productsRepository.save(products);
    }

    public Products getProductById(Long id) {
        return productsRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.PRODUCT_NOT_FOUND_MESSAGE, id)));
    }

    public List<Products> getAllProducts() {

        return productsRepository.getAllBy();
    }

    public void saveProduct(Products products) {

        productsRepository.save(products);
    }

    public Page<ProductsDTO> findAllWithPage(Pageable pageable) {
        Page<Products> productsPage = productsRepository.findAll(pageable);
        Page<ProductsDTO> productsDTOPage = productsPage.map(new Function<Products, ProductsDTO>() {
            @Override
            public ProductsDTO apply(Products products) {

                return productsMapper.productsToProductsDTO(products);
            }
        });
        return productsDTOPage;
    }

    public ProductsDTO findById(Long id) {
        Products products = getProduct(id);
        return productsMapper.productsToProductsDTO(products);
    }

    public Products getProduct(Long id) {
        Products product = productsRepository.findProductById(id).orElseThrow(() -> new
                ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));
        return product;
    }
}
