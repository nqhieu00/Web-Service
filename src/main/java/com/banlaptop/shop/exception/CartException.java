package com.banlaptop.shop.exception;

public class CartException extends GenericException{
    String message;
    public CartException(String message) {
        super(message);
        this.message=message;
    }
    public CartException(){

    }
}
