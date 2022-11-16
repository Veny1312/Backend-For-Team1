package com.solera.team1.project.team1project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.solera.team1.project.team1project.Service.ITaskService;
import com.solera.team1.project.team1project.Service.ITeamService;
import com.solera.team1.project.team1project.model.Task;
import com.solera.team1.project.team1project.model.Team;

@RestController
public class TaskController {
	@Autowired
	private ITaskService taskService;

	@Autowired
	private ITeamService teamService;

//@PostMapping("/tasks/create")
//public void createTask(@RequestBody Task task) {
//	taskService.create(task);	
//}

	@PostMapping("/tasks/{teamId}/create")
	public void createTask(@PathVariable String teamId, @RequestBody Task task) {
		Optional<Team> team = teamService.findById(teamId);
		taskService.create(task);
		team.get().addTask(task);
		team.get().calculateTotalPoints();
		teamService.update(team.get());
	}

	@GetMapping("/tasks")
	public List<Task> getAllTasks() {
		return taskService.findAll();
	}

	@DeleteMapping("/tasks/delete")
	public void deleteAllTasks() {
		taskService.deleteAll();
	}

	@GetMapping("/tasks/{teamId}")
	public List<Task> getTasksByTeamId(@PathVariable String teamId) {
		Optional<Team> team = teamService.findById(teamId);
		return team.get().getTasks();
	}

	@DeleteMapping("/tasks/delete/{teamId}")
	public void deleteTasksById(@PathVariable String teamId, @RequestBody Task task) {
		Optional<Team> team = teamService.findById(teamId);
		team.get().deleteTask(task);
		team.get().calculateTotalPoints();
		teamService.update(team.get());
		
	}

}