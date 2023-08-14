package com.example.demo5.Teams;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


import com.example.demo5.Footballers.Footballer;

@Entity
@Table
public class Team{
    @Id
    @SequenceGenerator(
        name = "team_seq",
        sequenceName = "team_seq",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "team_seq"
    )
    private int teamId;
    @NotBlank(message = "Team name can't be blank")
    private String teamName;
    @NotBlank(message = "League name can't be blank")
    private String league;
    @NotBlank(message = "Coach name can't be blank")
    private String coach;
    private int titles;
    @Valid
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "team")
    private List<Footballer> footballers = new ArrayList<>();
    public Team() {
    }
    public Team(int id, String name, String league, String coach, int titles, List<Footballer> footballers) {
        this.teamId = id;
        this.teamName = name;
        this.league = league;
        this.coach = coach;
        this.titles = titles;
        for (Footballer player:footballers){
            this.footballers.add(player);
        }
    }
    @Override
    public String toString() {
        String string = " ";
        for (Footballer footballer:footballers){
            string += (footballer.getName() + " "); 
        }
        return "TEAM ['coach'=" + coach + ", 'id'=" + teamId + ", 'league'=" + league + ", 'name'=" + teamName + ", 'titles'=" + titles
                + ", 'players'=(" + string + ")]";
    }
    public int getId() {
        return teamId;
    }
    public void setId(int id) {
        this.teamId = id;
    }
    public String getName() {
        return teamName;
    }
    public void setName(String name) {
        this.teamName = name;
    }
    public String getLeague() {
        return league;
    }
    public void setLeague(String league) {
        this.league = league;
    }
    public String getCoach() {
        return coach;
    }
    public void setCoach(String coach) {
        this.coach = coach;
    }
    public int getTitles() {
        return titles;
    }
    public void setTitles(int titles) {
        this.titles = titles;
    }
    public List<Footballer> getFootballers(){
        return this.footballers;
    }
    
}
