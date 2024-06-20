package com.example.productservice.services;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repository.CategoryRepo;
import com.example.productservice.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier("SelfProductService")
public class ProductServiceImpl implements ProductService{
    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;

    public ProductServiceImpl(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = productRepo.findById(id);
        return product.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        Optional<Category> categoryOptional = this.categoryRepo.findByName(product.getCategory().getName());
        if(categoryOptional.isPresent()){
            product.setCategory(categoryOptional.get());
        } else {
            Category category = categoryRepo.save(product.getCategory());
            product.setCategory(category);
        }
        return this.productRepo.save(product);
    }

        @Override
        public Product updateProduct(Product updatedProduct) {
        Product product = productRepo.findById(updatedProduct.getId()).get();

        if(product != null) {
          product.setTitle(updatedProduct.getTitle());
          product.setId(updatedProduct.getId());
          product.setPrice(updatedProduct.getPrice());
          product.setDescription(updatedProduct.getDescription());
          Category category = product.getCategory();
          if(category != null) {
              category.setName(updatedProduct.getCategory().getName());
          }
          product.setCategory(category);
        }
        return this.productRepo.save(product);
    }

    @Override
    public Product deleteProduct(Long id) {
     return null;
    }

    @Override
    public void deleteAllProducts() {

    }
}
