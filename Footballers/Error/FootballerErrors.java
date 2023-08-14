package com.example.demo5.Footballers.Error;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
@ControllerAdvice
public class FootballerErrors {
    @ExceptionHandler(ExistingFootballer.class)
    public ResponseEntity<String> errorExists(){
        return new ResponseEntity<>("This footballer already exists", HttpStatus.BAD_REQUEST);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidExcept.class)
    public ResponseEntity<String> validError(){
        return new ResponseEntity<>( ValidExcept.message + "\n" + " Request not valid", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NoFootballer.class)
    public ResponseEntity<String> errorNotFound(){
        return new ResponseEntity<>("There is no footballer with that id", HttpStatus.NOT_FOUND); // how do we pass message (instead of that fe)
    }
}
