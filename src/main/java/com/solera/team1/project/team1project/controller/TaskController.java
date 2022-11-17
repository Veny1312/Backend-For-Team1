package com.solera.team1.project.team1project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.solera.team1.project.team1project.Service.ITaskService;
import com.solera.team1.project.team1project.Service.ITeamService;
import com.solera.team1.project.team1project.Service.TaskService;
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
	@CrossOrigin(origins = "http://localhost:3000")
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

	@CrossOrigin(origins = "http://localhost:3000")
	@DeleteMapping("/tasks/delete/{taskId}")
	@ResponseStatus(code = HttpStatus.OK)
	public void deleteTasksById(@PathVariable String taskId) {
		taskService.delete(taskId);

	}
	@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:8080" })
	@ResponseStatus(code = HttpStatus.OK)
	@PatchMapping("/tasks/update/{taskId}")
	public void updateTask(@PathVariable String taskId, @RequestBody Task task) {
	Optional<Task> currentTask = taskService.findById(taskId);
	currentTask.get().setAccomplishment(task.getAccomplishment());
	currentTask.get().setPoints(task.getPoints());
	taskService.update(currentTask.get());
	

	}

}