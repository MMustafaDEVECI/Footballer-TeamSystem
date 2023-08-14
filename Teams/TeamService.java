package com.example.demo5.Teams;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo5.Footballers.Footballer;
import com.example.demo5.Footballers.Error.ExistingFootballer;
import com.example.demo5.main.TeamDto;

@Service
public class TeamService {
    private TeamRepo teamRepo;
    @Autowired
    public TeamService(TeamRepo teamRepo){
        this.teamRepo = teamRepo;
    }
    
    public List<TeamDto> getTeams(){
        List<Team> teams = teamRepo.findAll();
        List<TeamDto> teamDtos = new ArrayList<>();
        for (Team team:teams){
            teamDtos.add(convertTeamDto(team));
        }
        return teamDtos;
    }
    private TeamDto convertTeamDto(Team team){
        TeamDto teamDto = new TeamDto();
        teamDto.setTeamId(team.getId());
        teamDto.setTeamName(team.getName());
        teamDto.setLeague(team.getLeague());
        teamDto.setCoach(team.getCoach());
        teamDto.setTitles(team.getTitles());
        List<String> playerNames = new ArrayList<>();
        for (Footballer fb:team.getFootballers()){
            if(fbExists(fb))
            playerNames.add(fb.getName());
        }
        teamDto.setFbNames(playerNames);
        return teamDto;
    }
    public TeamDto addTeam(Team team){
        for (Footballer footballer:team.getFootballers()){
            if (fbExists(footballer)) throw new ExistingFootballer();
            footballer.setTeam(team);
        }

        teamRepo.save(team);
        return convertTeamDto(team);
    }
    public TeamDto getTeam(int teamId){
        return convertTeamDto(teamRepo.findById(teamId).get());
    }
    public void updateTeam(int teamId, Team team){
        Team teamOld = teamRepo.findById(teamId).get();
        teamOld.setCoach(team.getCoach());
        teamOld.setLeague(team.getLeague());
        teamOld.setName(team.getName());
        teamRepo.save(teamOld);
    }
    public void deleteTeam(int teamId){
        Team team = teamRepo.findById(teamId).get();
        teamRepo.delete(team);
    }
    public boolean teamExistsById(int teamId){
        return teamRepo.existsById(teamId);
    }
    public boolean teamExistsByName(String name){
        List<Team> teamList = teamRepo.findAll();
        boolean exist = false;
        for(Team team:teamList){
            if (name.equals(team.getName())){
                exist = true;
                break;
            }
        }
        return exist;
    }
    public Team getTeamByName(String teamName){
        List<Team> teamList = teamRepo.findAll();
        for(Team team:teamList){
            if (teamName.equals(team.getName())){
                return team;
            }
        }
        return null;
    }
    public boolean teamFbExists(Team team){
        List<Footballer> fbList = team.getFootballers();
        for(Footballer fb:fbList){
            if(fbExists(fb))
                return true;
        }
        return false; 
    }
    public boolean fbExists(Footballer footballer){
        List<Team> teamList = teamRepo.findAll();
        for (Team team:teamList){
            List<Footballer> fbList = team.getFootballers();
            for(Footballer fb:fbList){
                if ((fb.getName().equals(footballer.getName())) && (fb.getAge() == footballer.getAge()) && (footballer.getCountry().equals(fb.getCountry()) && (footballer.getPosition().equals(fb.getPosition())))){
                    return true;
                }
            }
        }
        return false; 
    }
}
