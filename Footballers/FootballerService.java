package com.example.demo5.Footballers;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo5.main.FbDto;


@Service
public class FootballerService {
    private FootballerRepo footballerRepo;
    @Autowired
    public FootballerService(FootballerRepo footballerRepo){
        this.footballerRepo = footballerRepo;
    }
    public List<FbDto> getFootballers(){
        return (footballerRepo
        .findAll())
        .stream()  
        .map(this::convertFbDto)  
        .collect(Collectors.toList());
    }
    private FbDto convertFbDto(Footballer footballer){
        FbDto fbDto = new FbDto();
        fbDto.setId(footballer.getFootballerId());
        fbDto.setName(footballer.getName());
        fbDto.setAge(footballer.getAge());
        fbDto.setJersey(footballer.getJersey());
        fbDto.setPosition(footballer.getPosition());
        fbDto.setCountry(footballer.getCountry());
        if (footballer.getTeam() == null){
            fbDto.setTeam("Free Agent");
        }
        else
        fbDto.setTeam(footballer.getTeam().getName());
        return fbDto;
    }
    public FbDto getFootballer(int footballerId){
        return convertFbDto(footballerRepo.findById(footballerId).get());
    }
    public FbDto addFootballer(Footballer footballer){
        footballerRepo.save(footballer);
        return convertFbDto(footballer);
    }
    public void updateFootballer(Footballer footballer, int footballerId){
        Footballer oldFootballer = footballerRepo.findById(footballerId).get();
        if (footballer.getAge() != 0) oldFootballer.setAge(footballer.getAge());
        if (footballer.getName() != null) oldFootballer.setName(footballer.getName()); // better way?
        if (footballer.getPosition() != null) oldFootballer.setPosition(footballer.getPosition());
        footballerRepo.save(oldFootballer);
    }
    public boolean footballerExistsById(int id){
        return footballerRepo.existsById(id);
    }
    public void deleteFootballer(int id){
        footballerRepo.deleteById(id);
    }
    public boolean footballerExists(Footballer footballer){
        boolean exists = false;
        List<Footballer> list = footballerRepo.findAll();
        for (Footballer fb:list){
            if ((fb.getName().equals(footballer.getName())) && (fb.getAge() == footballer.getAge()) && (footballer.getCountry().equals(fb.getCountry()) && (footballer.getPosition().equals(fb.getPosition())))){
                exists = true;
                break;
            }
        }
        return exists;
    }
}
