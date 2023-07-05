package com.example.demo5.Footballers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FootballerRepo extends JpaRepository<Footballer, Integer>{
}
