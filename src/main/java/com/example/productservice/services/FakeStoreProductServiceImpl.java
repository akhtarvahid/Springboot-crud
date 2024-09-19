package com.example.productservice.services;

import com.example.productservice.dtos.FakeStoreProductDto;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.thirdpartyclients.FakeStoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Primary
@Service("FakeProductService")
public class FakeStoreProductServiceImpl implements ProductService{
    private FakeStoreClient fakeStoreClient;
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public FakeStoreProductServiceImpl(FakeStoreClient fakeStoreClient,  RedisTemplate redisTemplate) {
        this.fakeStoreClient = fakeStoreClient;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        return fakeStoreClient.getProductById(id);
    }

    @Override
    public Page<Product> getAllProducts(int pageNo, int pageSize, String title) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new LinkedList<>();
        for (FakeStoreProductDto fakeStoreProductDto: fakeStoreClient.getAllProducts()) {
            products.add(getProductsFromFakeStoreProductDto(fakeStoreProductDto));
        }
        return products;
    }

    @Override
    public Product createProduct(Product product) {
        return getProductsFromFakeStoreProductDto(fakeStoreClient.createProduct(getFakeStoreProductDtoFromProduct(product)));
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public Product deleteProduct(Long id) {
        return getProductsFromFakeStoreProductDto(fakeStoreClient.deleteProduct(id));
    }

    @Override
    public List<Product> deleteAllProducts(List<Long> ids) {
        return null;
    }

    private Product getProductsFromFakeStoreProductDto(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
//        product.setId(fakeStoreProductDto.getId());
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