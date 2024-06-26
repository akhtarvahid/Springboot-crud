package com.example.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
//    private Long id;
    private String title;
    private String description;
    private Double price;
    // One Category can have multiple products
    @ManyToOne
    private Category category;
}
