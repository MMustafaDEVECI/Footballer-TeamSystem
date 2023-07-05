package com.example.demo5.Footballers;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.example.demo5.Teams.Team;



@Entity
@Table
public class Footballer{
    @Id
    @SequenceGenerator(
        name = "fb_seq",
        sequenceName = "fb_seq",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "fb_seq"
    )
    private int id;
    @NotBlank(message = "Footballer name can't be blank")
    private String name;
    private int age;
    private int jersey;
    @NotBlank(message = "Position name can't be blank")
    private String position;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
    @NotBlank(message = "Country name can't be blank")
    private String country;
    public Footballer(String name, int age, int jersey, String position, Team team, String country) {
        this.name = name;
        this.age = age;
        this.jersey = jersey;
        this.position = position;
        this.team = team;
        this.country = country;
    }
    public Footballer(){

    }

    public int getFootballerId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getJersey() {
        return jersey;
    }

    public String getPosition() {
        return position;
    }

    public Team getTeam() {
        return team;
    }

    public String getCountry() {
        return country;
    }

    public void setFootballerId(int footballerId) {
        this.id = footballerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setJersey(int jersey) {
        this.jersey = jersey;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "FOOTBALLER [name= " + name + ", age= "+ age + ", country=" + country + ", jersey="
                + jersey + ", position=" + position + ", footballerId=" + id + "]";
    }
}