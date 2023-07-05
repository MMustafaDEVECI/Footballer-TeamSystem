package com.example.demo5.Footballers;

import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo5.Footballers.Error.ExistingFootballer;
import com.example.demo5.Footballers.Error.NoFootballer;
import com.example.demo5.Footballers.Error.ValidExcept;
import com.example.demo5.main.FbDto;

@RestController
@RequestMapping(path = "api/v5/Footballers")
public class FootballerController {
    private FootballerService footballerService;
    @Autowired
    public FootballerController(FootballerService footballerService){
        this.footballerService = footballerService;
    }
    @GetMapping()
    public ResponseEntity<List<FbDto>> getFootballers(){
        return new ResponseEntity<>(footballerService.getFootballers(), HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<FbDto> addFootballer(@Valid @RequestBody Footballer footballer, BindingResult br){
        if(footballerService.footballerExists(footballer)) throw new ExistingFootballer();
        if(br.hasErrors()){
            FieldError string = br.getFieldError();
            throw new ValidExcept(string);
        }
        FbDto newFootballer = footballerService.addFootballer(footballer);
        return new ResponseEntity<>(newFootballer,HttpStatus.OK);
    }
    @GetMapping("/{footballerId}")
    public ResponseEntity<FbDto> getFootballer(@PathVariable ("footballerId") int footballerId){
        if(footballerService.footballerExistsById(footballerId)) throw new NoFootballer();
        FbDto footballer = footballerService.getFootballer(footballerId);
        return new ResponseEntity<>(footballer,HttpStatus.OK);
    }
    @PutMapping("/{footballerId}")
    public ResponseEntity<FbDto> updateFootballer(@Valid @RequestBody Footballer footballer, @PathVariable ("footballerId") int footballerId){
        if(!footballerService.footballerExistsById(footballerId)) throw new NoFootballer();
        footballerService.updateFootballer(footballer, footballerId);
        FbDto footballerNew = footballerService.getFootballer(footballerId);
        return new ResponseEntity<>(footballerNew,HttpStatus.OK);
    }
    @DeleteMapping("/{footballerId}")
    public ResponseEntity<FbDto> deleteFootballer(@PathVariable ("footballerId") int footballerId){
        if(!footballerService.footballerExistsById(footballerId)) throw new NoFootballer();
        FbDto footballer = footballerService.getFootballer(footballerId);
        footballerService.deleteFootballer(footballerId);
        return new ResponseEntity<>(footballer,HttpStatus.OK);
    }
    
}
