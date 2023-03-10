package com.scm.repository;

import com.scm.domain.Products;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {

    Optional<Products> findByProductName(String productName);

    @EntityGraph(attributePaths = "image")
    Optional<Products> findProductById(Long id);

    @EntityGraph(attributePaths = "id")
    List<Products> getAllBy();
}
