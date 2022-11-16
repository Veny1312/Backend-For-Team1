package com.solera.team1.project.team1project.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.solera.team1.project.team1project.Service.ITeamService;
import com.solera.team1.project.team1project.model.Task;
import com.solera.team1.project.team1project.model.Team;

@RestController
public class TeamController {
	
	@Autowired
	private ITeamService teamService;
	
	@GetMapping("/all")
	public List<Team> getAllTeams() {
		return teamService.findAll();
	}
	
	@GetMapping("/all/{id}/tasks")
	 public List<Task> getTasksForTeam(@PathVariable String id) {
		 Optional<Team> team = teamService.findById(id);
		 return team.get().getTasks();
	 }
	
	@PostMapping("/create")
	public Team create(@RequestBody Team team) {
		team.setId(UUID.randomUUID().toString());
		return teamService.create(team);
	}
	
	@PostMapping("/update")
	public Team update(@RequestBody Team team) {
		return teamService.update(team);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteById (@PathVariable String id) {
		teamService.delete(id);
	}
	
}