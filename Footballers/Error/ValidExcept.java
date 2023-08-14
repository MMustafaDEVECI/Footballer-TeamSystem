package com.example.demo5.Footballers.Error;

import org.springframework.validation.FieldError;

public class ValidExcept extends RuntimeException{
    public static String message = " ";
    
    public ValidExcept(FieldError arg0){
        super();
        message = arg0.getDefaultMessage();
    }
    
}
