package com.example.productservice.controllers;

import com.example.productservice.common.AuthCommons;
import com.example.productservice.dtos.userServiceConnection.UserDto;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    private AuthCommons authCommons;
    private RestTemplate restTemplate;

    @Autowired
    public ProductController(@Qualifier("SelfProductService") ProductService productService, AuthCommons authCommons, RestTemplate restTemplate) {
        this.productService = productService;
        this.authCommons = authCommons;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        ResponseEntity<Product> responseEntity;

        Product product = productService.getProductById(id);
        responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        return responseEntity.getBody();
    }

    @GetMapping("/single/{id}")
    public Product getSingleProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        ResponseEntity<Product> responseEntity;

        System.out.println("API-GATEWAY PRODUCT" + id);
       // Make a call to user-service
        UserDto userDto = restTemplate.getForObject("http://userservice/users/" + id, UserDto.class);

        Product product = productService.getSingleProduct(id);
        responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        return responseEntity.getBody();
    }

    @GetMapping()
    public Page<Product> getProducts(
            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "1000") int pageSize,
            @RequestParam(value = "sort", defaultValue = "id") String title
    ) {
        Page<Product> products = productService.getAllProducts(pageNo, pageSize, title);

        return ResponseEntity.ok(products).getBody();
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
