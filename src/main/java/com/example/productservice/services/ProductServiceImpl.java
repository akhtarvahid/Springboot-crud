package com.example.productservice.services;

import com.example.productservice.models.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("SelfProductService")
public class ProductServiceImpl implements ProductService{

    @Override
    public Product getProductById(Long id) {
         return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
      return null;
    }

    @Override
    public void updateProduct() {

    }

    @Override
    public Product deleteProduct(Long id) {
     return null;
    }

    @Override
    public void deleteAllProducts() {

    }
}
