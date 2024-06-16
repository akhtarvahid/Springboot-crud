package com.example.productservice.services;

import com.example.productservice.dtos.FakeStoreProductDto;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;

@Service("FakeProductService")
public class FakeStoreProductServiceImpl implements ProductService{

    private RestTemplateBuilder restTemplateBuilder;
    private String getProductUrl = "https://fakestoreapi.com/products/{id}";
    private String genericProductUrl = "https://fakestoreapi.com/products";

    @Autowired
    public FakeStoreProductServiceImpl(@Qualifier("restTemplateBuilder") RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(getProductUrl, FakeStoreProductDto.class, id);
        if(responseEntity.getBody() == null) {
            // Throw exception
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
        return getProductsFromFakeStoreProductDto(responseEntity.getBody());
    }

    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> responseEntity = restTemplate.getForEntity(genericProductUrl, FakeStoreProductDto[].class);
        List<Product> list = new LinkedList<>();
        for (FakeStoreProductDto fakeStoreProductDto: responseEntity.getBody()) {
            list.add(getProductsFromFakeStoreProductDto(fakeStoreProductDto));
        }
        return list;
    }

    @Override
    public Product createProduct(Product product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.postForEntity(genericProductUrl, getFakeStoreProductDtoFromProduct(product), FakeStoreProductDto.class);
        return getProductsFromFakeStoreProductDto(responseEntity.getBody());
    }

    @Override
    public void updateProduct() {

    }

    @Override
    public Product deleteProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.delete(getProductUrl, id);
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(getProductUrl, FakeStoreProductDto.class, id);
        return getProductsFromFakeStoreProductDto(responseEntity.getBody());
    }

    @Override
    public void deleteAllProducts() {

    }

    private Product getProductsFromFakeStoreProductDto(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        product.setPrice(fakeStoreProductDto.getPrice());
        return product;
    }
    private FakeStoreProductDto getFakeStoreProductDtoFromProduct(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        fakeStoreProductDto.setPrice(product.getPrice());
        return fakeStoreProductDto;
    }
}
