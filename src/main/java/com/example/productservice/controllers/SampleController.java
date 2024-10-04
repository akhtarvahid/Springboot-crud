package com.example.productservice.controllers;

import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@NoArgsConstructor
@RestController
@RequestMapping("/sample")
public class SampleController {
    @GetMapping("/{name}")
    public String getProduct(@PathVariable("name") String name) {
        return "Service is UP";
    }
}
