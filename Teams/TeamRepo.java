package com.example.demo5.Teams;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TeamRepo extends JpaRepository<Team,Integer>{}
