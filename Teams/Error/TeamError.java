package com.example.demo5.Teams.Error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo5.Footballers.Error.ValidExcept;

@ControllerAdvice
public class TeamError {
    @ExceptionHandler(ExistingTeam.class)
    public ResponseEntity<String> errorExists(){
        return new ResponseEntity<>("This team already exists", HttpStatus.BAD_REQUEST);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidExcept.class)
    public ResponseEntity<String> errorNotValid(String string){
        return new ResponseEntity<>( string + "\n" + " Request not valid", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NoTeam.class)
    public ResponseEntity<String> errorNotFound(){
        return new ResponseEntity<>("There is no team with that id", HttpStatus.NOT_FOUND); // how do we pass message (instead of that fe)
    }
    @ExceptionHandler(ExistingFootballerinTeam.class)
    public ResponseEntity<String> errorplayerFound(){
        return new ResponseEntity<>("You can't register a player that is already registered to a team", HttpStatus.NOT_FOUND); // how do we pass message (instead of that fe)
    }
}
