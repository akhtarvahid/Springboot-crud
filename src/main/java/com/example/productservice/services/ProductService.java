package com.example.productservice.services;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id) throws ProductNotFoundException;
    Page<Product> getAllProducts(int pageNo, int pageSize);
    Product createProduct(Product product);
    Product updateProduct(Product product);
    Product deleteProduct(Long id);
    List<Product> deleteAllProducts(List<Long> ids);
}
