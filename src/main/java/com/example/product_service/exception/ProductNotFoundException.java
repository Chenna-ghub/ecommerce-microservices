package com.example.product_service.exception;

import com.example.product_service.repository.ProductRepository;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String message){
        super(message);
    }

}
