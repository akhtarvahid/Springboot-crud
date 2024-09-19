package com.example.productservice.controllers;

import com.example.productservice.common.AuthCommons;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    private AuthCommons authCommons;

    @Autowired
    public ProductController(@Qualifier("FakeProductService") ProductService productService, AuthCommons authCommons) {
        this.productService = productService;
        this.authCommons = authCommons;
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        ResponseEntity<Product> responseEntity;

        Product product = productService.getProductById(id);
        responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        return responseEntity.getBody();
    }

    @GetMapping()
    public Page<Product> getProducts(@RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                                     @RequestParam(value = "pageSize", defaultValue = "1000") int pageSize,
                                     @RequestParam(value = "sort", defaultValue = "id") String title) {
        return productService.getAllProducts(pageNo, pageSize, title);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        System.out.println(product.getTitle());

        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        return productService.deleteProduct(id);
    }

    @DeleteMapping("/bulkDelete/{id}")
    public List<Product> getAllProducts(@PathVariable("id") List<Long> ids) throws ProductNotFoundException {
        return productService.deleteAllProducts(ids);
    }

//    public  String getProductByCategory(String category) {
//
//    }

}
