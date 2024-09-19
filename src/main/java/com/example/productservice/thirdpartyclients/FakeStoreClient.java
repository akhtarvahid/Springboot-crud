package com.example.productservice.thirdpartyclients;

import com.example.productservice.dtos.FakeStoreProductDto;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

@Component
public class FakeStoreClient {

    private final RedisTemplate redisTemplate;
    private RestTemplateBuilder restTemplateBuilder;
    private String genericProductUrl = "https://fakestoreapi.com/products";
    private String getProductUrl;

    @Autowired
    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder, @Value("${fakeStore.api.url}") String fakeStoreApiUrl, RedisTemplate redisTemplate) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.getProductUrl = fakeStoreApiUrl;
        this.redisTemplate = redisTemplate;
    }


    public Product getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        Product productResult = (Product) redisTemplate.opsForHash().get("PRODUCTS", "PRODUCTS_" + id);

        if (productResult != null) {
            // CACHE hit
            return productResult;
        }


        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(getProductUrl, FakeStoreProductDto.class, id);
        Product response = getProductsFromFakeStoreProductDto(responseEntity.getBody());

        redisTemplate.opsForHash().put("PRODUCTS", "PRODUCTS_" + id, response);

        return response;
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


    public FakeStoreProductDto[] getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> responseEntity = restTemplate.getForEntity(genericProductUrl, FakeStoreProductDto[].class);
        return responseEntity.getBody();
    }


    public FakeStoreProductDto createProduct(FakeStoreProductDto fakeStoreProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.postForEntity(genericProductUrl, fakeStoreProductDto, FakeStoreProductDto.class);
        return responseEntity.getBody();
    }


    public void updateProduct() {

    }


    public FakeStoreProductDto deleteProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.execute(getProductUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        return responseEntity.getBody();
    }


    public void deleteAllProducts() {

    }
}