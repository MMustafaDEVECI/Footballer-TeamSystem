package com.example.demo5.Teams;


import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo5.Footballers.Error.ValidExcept;
import com.example.demo5.Teams.Error.ExistingFootballerinTeam;
import com.example.demo5.Teams.Error.ExistingTeam;
import com.example.demo5.Teams.Error.NoTeam;
import com.example.demo5.main.TeamDto;

@RestController
@RequestMapping(path = "api/v5/Teams")
public class TeamController {
    private TeamService teamService;
    @Autowired
    public TeamController(TeamService teamService){
        this.teamService = teamService;
    }
    @GetMapping()
    public List<TeamDto> getTeams(){
        return teamService.getTeams();
    }
    @PostMapping()
    public ResponseEntity<TeamDto> addTeam(@Validated @RequestBody Team team, BindingResult br){
        if(teamService.teamExistsByName(team.getName())) throw new ExistingTeam();
        if(teamService.teamFbExists(team)) throw new ExistingFootballerinTeam();
        if(br.hasErrors()){ 
            FieldError string = br.getFieldError();
            throw new ValidExcept(string);
        }
        TeamDto newTeam = teamService.addTeam(team);
        return new ResponseEntity<>(newTeam, HttpStatus.OK);
    }
    @PutMapping("/{teamId}")
    public ResponseEntity<TeamDto> updateTeam(@PathVariable ("teamId") int teamId, @Valid @RequestBody Team team){
        if (!teamService.teamExistsById(teamId)) throw new NoTeam();
        teamService.updateTeam(teamId,team);
        TeamDto newTeam = teamService.getTeam(teamId);
        return new ResponseEntity<>(newTeam, HttpStatus.OK);
    }
    @DeleteMapping("/{teamId}")
    public ResponseEntity<TeamDto> deleteTeam(@PathVariable ("teamId") int teamId){
        if (!teamService.teamExistsById(teamId)) throw new NoTeam();
        TeamDto team = teamService.getTeam(teamId);
        teamService.deleteTeam(teamId);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }
    @GetMapping("/{teamId}")
    public ResponseEntity<TeamDto> getTeam(@PathVariable ("teamId") int teamId){
        if (!teamService.teamExistsById(teamId)) throw new NoTeam();
        TeamDto team = teamService.getTeam(teamId);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }
}
