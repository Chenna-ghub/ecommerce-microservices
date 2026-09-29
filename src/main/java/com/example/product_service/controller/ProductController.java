package com.example.product_service.controller;

import com.example.product_service.dto.ProductRequest;
import com.example.product_service.dto.ProductResponse;
import com.example.product_service.enums.ProductStatus;
import com.example.product_service.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest request){
        ProductResponse response = productService.createProduct(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponse>> getAllProducts(){

        return ResponseEntity.ok(productService.getAllProducts());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id){

        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequest request){

        ProductResponse response = productService.updateProduct(id, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProdcutById(@PathVariable Long id){

        productService.deleteProductById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAllProductDetails(Pageable pageable){
        return ResponseEntity.ok(productService.getAllProductsDetails(pageable));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Page<ProductResponse>> getProductById(@PathVariable Long categoryId, Pageable pageable){

        return ResponseEntity.ok(productService.getProductByCategory(categoryId, pageable));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<Page<ProductResponse>> getProductsByStatus(@PathVariable ProductStatus status, Pageable pageable){

        return ResponseEntity.ok(productService.getProductsBuStatus(status, pageable));
    }

    @GetMapping("/minPrice/{minPrice}/maxPrice/{maxPrice}")
    public ResponseEntity<Page<ProductResponse>> getProductsByPrice(
            @PathVariable BigDecimal minPrice, @PathVariable BigDecimal maxPrice, Pageable pageable){

        return ResponseEntity.ok(productService.getProductsByPrice(minPrice, maxPrice, pageable));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Page<ProductResponse>> getProductByName(
            @PathVariable String name, Pageable pageable){

        return ResponseEntity.ok(productService.getProductsByName(name, pageable));
    }

}
