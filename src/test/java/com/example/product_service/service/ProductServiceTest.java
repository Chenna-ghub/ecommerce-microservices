package com.example.product_service.service;

import com.example.product_service.dto.ProductRequest;
import com.example.product_service.dto.ProductResponse;
import com.example.product_service.entity.Product;
import com.example.product_service.enums.ProductStatus;
import com.example.product_service.exception.ProductNotFoundException;
import com.example.product_service.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getProductById_shouldReturnProduct() {

        Product product = new Product();

        product.setId(1L);
        product.setName("Laptop");
        product.setPrice(new BigDecimal("65000.00"));
        product.setCategoryId(10L);
        product.setStatus(ProductStatus.ACTIVE);

        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));

        ProductResponse response =
                productService.getProductById(1L);

        assertEquals(1L, response.getId());
        assertEquals("Laptop", response.getName());
        assertEquals(
                new BigDecimal("65000.00"),
                response.getPrice()
        );

        verify(productRepository)
                .findById(1L);
    }
    @Test
    void getProductById_shouldThrowExceptionWhenProductNotFound() {

        when(productRepository.findById(999L))
                .thenReturn(Optional.empty());

        ProductNotFoundException exception =
                assertThrows(
                        ProductNotFoundException.class,
                        () -> productService.getProductById(999L)
                );

        assertEquals(
                "Product not found with id: 999",
                exception.getMessage()
        );

        verify(productRepository)
                .findById(999L);
    }
    @Test
    void createProduct_shouldSaveAndReturnProduct() {

        ProductRequest request = new ProductRequest();

        request.setName("Laptop");
        request.setDescription("Business Laptop");
        request.setPrice(new BigDecimal("65000.00"));
        request.setCategoryId(10L);
        request.setStatus(ProductStatus.ACTIVE);

        Product savedProduct = new Product();

        savedProduct.setId(1L);
        savedProduct.setName("Laptop");
        savedProduct.setDescription("Business Laptop");
        savedProduct.setPrice(new BigDecimal("65000.00"));
        savedProduct.setCategoryId(10L);
        savedProduct.setStatus(ProductStatus.ACTIVE);

        when(productRepository.save(any(Product.class)))
                .thenReturn(savedProduct);

        ProductResponse response =
                productService.createProduct(request);

        assertEquals(1L, response.getId());
        assertEquals("Laptop", response.getName());
        assertEquals(
                new BigDecimal("65000.00"),
                response.getPrice()
        );

        verify(productRepository)
                .save(any(Product.class));
    }

    @Test
    void updateProduct_shouldUpdateExistingProduct() {

        Long productId = 1L;

        ProductRequest request = new ProductRequest();

        request.setName("Updated Laptop");
        request.setDescription("Updated Description");
        request.setPrice(new BigDecimal("70000.00"));
        request.setCategoryId(20L);
        request.setStatus(ProductStatus.ACTIVE);

        Product existingProduct = new Product();

        existingProduct.setId(productId);
        existingProduct.setName("Laptop");
        existingProduct.setPrice(new BigDecimal("65000.00"));
        existingProduct.setCategoryId(10L);
        existingProduct.setStatus(ProductStatus.ACTIVE);

        when(productRepository.findById(productId))
                .thenReturn(Optional.of(existingProduct));

        when(productRepository.save(existingProduct))
                .thenReturn(existingProduct);

        ProductResponse response =
                productService.updateProduct(
                        productId,
                        request
                );

        assertEquals("Updated Laptop", response.getName());
        assertEquals(
                new BigDecimal("70000.00"),
                response.getPrice()
        );
        assertEquals(20L, response.getCategoryId());

        verify(productRepository)
                .findById(productId);

        verify(productRepository)
                .save(existingProduct);
    }

    @Test
    void updateProduct_shouldThrowExceptionWhenProductNotFound() {

        Long productId = 999L;

        ProductRequest request = new ProductRequest();

        request.setName("Laptop");
        request.setPrice(new BigDecimal("65000.00"));
        request.setCategoryId(10L);
        request.setStatus(ProductStatus.ACTIVE);

        when(productRepository.findById(productId))
                .thenReturn(Optional.empty());

        assertThrows(
                ProductNotFoundException.class,
                () -> productService.updateProduct(
                        productId,
                        request
                )
        );

        verify(productRepository)
                .findById(productId);

        verify(productRepository, never())
                .save(any(Product.class));
    }
    @Test
    void deleteProduct_shouldDeleteExistingProduct() {

        Long productId = 1L;

        Product product = new Product();
        product.setId(productId);
        product.setName("Laptop");

        when(productRepository.findById(productId))
                .thenReturn(Optional.of(product));

        productService.deleteProductById(productId);

        verify(productRepository)
                .findById(productId);

        verify(productRepository)
                .deleteById(productId);
    }
    @Test
    void deleteProduct_shouldThrowExceptionWhenProductNotFound() {

        Long productId = 999L;

        when(productRepository.findById(productId))
                .thenReturn(Optional.empty());

        assertThrows(
                ProductNotFoundException.class,
                () -> productService.deleteProductById(productId)
        );

        verify(productRepository)
                .findById(productId);

        verify(productRepository, never())
                .delete(any(Product.class));
    }
}
