package com.example.redisapp.controller;

import com.example.redisapp.dto.ProductDto;
import com.example.redisapp.repository.ProductRepository;
import com.example.redisapp.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/store")
public class ProductController {

    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getById")
    public ProductDto getById(Long id){
        return productService.findById(id);
    }

    @PostMapping("/createProduct")
 public ProductDto createProduct(ProductDto productDto){
        return productService.createProduct(productDto);
 }


}
