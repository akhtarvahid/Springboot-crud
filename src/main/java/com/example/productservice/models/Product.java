package com.example.productservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String title;
    private String description;
    private Double price;
    // One Category can have multiple products
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="product_id")
    @JsonIgnoreProperties("products")
    private Category category;

    private Integer rating;
}
