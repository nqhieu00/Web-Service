package com.banlaptop.shop.exception;

public class ProductException extends GenericException {
    private String message;
    public ProductException(){

    }
    public ProductException(String message){
        super(message);
        this.message=message;
    }
}
