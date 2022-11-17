package com.solera.team1.project.team1project.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.solera.team1.project.team1project.Service.ITeamService;
import com.solera.team1.project.team1project.exceptions.NotFoundException;
import com.solera.team1.project.team1project.model.Task;
import com.solera.team1.project.team1project.model.Team;

@RestController
public class TeamController {
	
	@Autowired
	private ITeamService teamService;
	
	@GetMapping("/all")
	public List<Team> getAllTeams() {
		if(teamService.findAll()!=null)
		return teamService.findAll();
		else
			throw new NotFoundException("Teams not found!");
	}
	
	@GetMapping("/all/{id}/tasks")
	 public List<Task> getTasksForTeam(@PathVariable String id) {
		if(teamService.findById(id)!=null)
		{		 Optional<Team> team = teamService.findById(id);
		if(team.get().getTasks()!=null) {
			return team.get().getTasks();
		}
		else
			throw new NotFoundException("Tasks not found!");
		 }
		else
			throw new NotFoundException("Team not found!");
	 }
	
	@PostMapping("/create")
	public Team create(@RequestBody @Validated Team team) {
		
		return teamService.create(team);
	}
	
	@PostMapping("/update")
	public Team update(@RequestBody @Validated Team team) {
		return teamService.update(team);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteById (@PathVariable String id) {
		if(id!="" && id!=null)
		{
			teamService.delete(id);
		}
		else
			throw new NotFoundException("Team not found!");

			
	}
	
}