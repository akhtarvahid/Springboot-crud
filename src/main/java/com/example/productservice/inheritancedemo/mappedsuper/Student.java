package com.example.productservice.inheritancedemo.mappedsuper;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Student extends User {
    private int psp;
    private String batch;
}
