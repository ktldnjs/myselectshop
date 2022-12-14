package com.siwon.myselectshop.controller;

import com.siwon.myselectshop.models.Product;
import com.siwon.myselectshop.models.ProductMypriceRequestDto;
import com.siwon.myselectshop.models.ProductRepository;
import com.siwon.myselectshop.models.ProductRequestDto;
import com.siwon.myselectshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 관심상품 조회기능.
@RestController // JSON 으로 응답
@RequiredArgsConstructor // productController 를 사용할 때는 반드시 productRepository 가 있어야 된다고 스프링에게 알려주는 것.
public class ProductRestController {

    private final ProductRepository productRepository;
    private final ProductService productService;

    @GetMapping("/api/products")
    public List<Product> readProducts() {
        return productRepository.findAll();
    }

    @PostMapping("/api/products")
    public Product createProduct(@RequestBody ProductRequestDto productRequestDto){
        Product product = new Product(productRequestDto);
        return productRepository.save(product);
    }

    @PutMapping("/api/products/{id}")
    public Long updateProduct(@PathVariable Long id, @RequestBody ProductMypriceRequestDto productMypriceRequestDto){
        return productService.update(id, productMypriceRequestDto);
    }


}
