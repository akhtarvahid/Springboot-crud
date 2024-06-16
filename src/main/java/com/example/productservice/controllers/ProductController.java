package com.example.productservice.controllers;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
//    @Autowired
    private ProductService productService;

    @Autowired
    public ProductController(@Qualifier("FakeProductService") ProductService productService) {
        this.productService = productService;
    }

//    @Autowired
//    public void setProductService(ProductService productService) {
//        this.productService = productService;
//    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
      return productService.getProductById(id);
    }

    @GetMapping()
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        System.out.println(product);

        return productService.createProduct(product);
    }
//    public  String getProductByCategory(String category) {
//
//    }

}
