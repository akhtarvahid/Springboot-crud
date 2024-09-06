package com.example.productservice.controllers;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    @Test
    void getProductById() throws ProductNotFoundException {
        // Arrange
        Product dummyProduct = new Product();
        dummyProduct.setId(1L);
        dummyProduct.setTitle("Dummy test product title");
        when(productService.getProductById(1L)).thenReturn(dummyProduct);

        // Act
        Product product = productController.getProduct(1L);

        // Assert
        assertEquals(1L, product.getId());
    }

    @Test
    void getProductByIdThrowsException() throws ProductNotFoundException {
        // Arrange
        Product dummyProduct = new Product();
        dummyProduct.setId(1L);
        dummyProduct.setTitle("Dummy test product title");
        when(productService.getProductById(1L)).thenThrow(new ProductNotFoundException("Product not found"));



        // Assert
        assertThrows(ProductNotFoundException.class, ()-> productController.getProduct(1L));
    }
}