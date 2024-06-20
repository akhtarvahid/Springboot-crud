package com.example.productservice.inheritancedemo.tableperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "tpc_student")
@Getter
@Setter
public class Student extends User {
    private int psp;
    private String batch;
}
