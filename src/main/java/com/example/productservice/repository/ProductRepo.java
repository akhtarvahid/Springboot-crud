package com.example.productservice.repository;

import com.example.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepo extends JpaRepository<Product, Long> {
    @Override
    Optional<Product> findById(Long uuid);
    List<Product> findAll();
}
