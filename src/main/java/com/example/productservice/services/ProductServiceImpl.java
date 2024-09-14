package com.example.productservice.services;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repository.CategoryRepo;
import com.example.productservice.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Primary
@Service("SelfProductService")
public class ProductServiceImpl implements ProductService {
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
    public Page<Product> getAllProducts(int pageNo, int pageSize) {
        return productRepo.findAll(PageRequest.of(pageNo, pageSize));
    }

    @Override
    public Product createProduct(Product product) {
//        Optional<Category> categoryOptional = this.categoryRepo.findByName(product.getCategory().getName());
//        if (categoryOptional.isPresent()) {
//            product.setCategory(categoryOptional.get());
//        } else {
//            Category category = categoryRepo.save(product.getCategory());
//            product.setCategory(category);
//        }
        return this.productRepo.save(product);
    }

    @Override
    public Product updateProduct(Product updatedProduct) {
        Product product = productRepo.findById(updatedProduct.getId()).get();

        if (product != null) {
            product.setTitle(updatedProduct.getTitle());
            product.setId(updatedProduct.getId());
            product.setPrice(updatedProduct.getPrice());
            product.setDescription(updatedProduct.getDescription());
            Category category = product.getCategory();
            if (category != null) {
                category.setName(updatedProduct.getCategory().getName());
            }
            product.setCategory(category);
        }
        return this.productRepo.save(product);
    }

    @Override
    public Product deleteProduct(Long id) {
        Optional<Product> product = productRepo.findById(id);
        if (product.isPresent()) {
            productRepo.delete(product.get());
        }

        return product.orElse(null);
    }

    @Override
    public List<Product> deleteAllProducts(List<Long> ids) {
        List<Product> listOfProducts = new LinkedList<>();
        if (ids.size() > 0) {
            for (Long id : ids) {
                Optional<Product> product = productRepo.findById(id);
                if (product.isPresent()) {
                    productRepo.delete(product.get());
                }
                listOfProducts.add(product.get());
            }
        } else {
            return null;
        }

        return listOfProducts;
    }
}
