package com.example.productservice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomTest {

    @Test
    void calculate(){
        int i = 4 + 4;
        assertTrue(i == 8);
        assertTrue(i % 2 == 0);
        assertTrue(i % 3 != 0);
    }
}