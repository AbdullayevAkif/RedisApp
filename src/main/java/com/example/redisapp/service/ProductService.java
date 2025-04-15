package com.example.redisapp.service;

import com.example.redisapp.Product;
import com.example.redisapp.dto.ProductDto;
import com.example.redisapp.repository.ProductRepository;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private static final String CACHE_NAME = "products";


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {

        return productRepository.findAll();
    }


    @CachePut(value = CACHE_NAME, key = "#result.id()")
    public ProductDto createProduct(ProductDto productDto) {

        Product product = new Product();
        product.setName(productDto.name());
        product.setPrice(productDto.price());

        Product savedProduct = productRepository.save(product);

        return new ProductDto(savedProduct.getId(), product.getName(), product.getPrice());


    }


    @Cacheable(value = "products", key = "#result.id()")
    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return new ProductDto(product.getId(), product.getName(), product.getPrice());
    }


}
