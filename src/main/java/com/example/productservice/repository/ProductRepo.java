package com.example.productservice.repository;

import com.example.productservice.models.Product;
import com.example.productservice.projections.IdTitleProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepo extends JpaRepository<Product, Long> {
    @Override
    Optional<Product> findById(Long uuid);
    List<Product> findAll();

    // HQL query
    @Query("select p.id as id, p.title as title from Product p where p.title = :t")
    List<IdTitleProduct> findAllIdTitleProducts(@Param("t") String title);

    // Native query (:product_title is a parameter)
    @Query(value = "select p.id, p.title from Product p where p.title = :product_title", nativeQuery = true)
    List<IdTitleProduct> findAllIdTitleProductsWithNative(@Param("product_title") String title);
}
