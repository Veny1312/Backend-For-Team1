package com.solera.team1.project.team1project.controller;

import java.util.List;
import java.util.Optional;
import org.apache.tomcat.websocket.AsyncChannelWrapperNonSecure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
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
import com.solera.team1.project.team1project.exceptions.NotFoundException;
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
	public void createTask(@PathVariable String teamId, @Validated @RequestBody Task task) {
		if (teamId != "" && teamId != null) {
			Optional<Team> team = teamService.findById(teamId);
			taskService.create(task);
			if (team.get() != null) {
				team.get().addTask(task);
				team.get().calculateTotalPoints();
				teamService.update(team.get());
			} else
				throw new NotFoundException("Error creating task!");
		}
	}

	@GetMapping("/tasks")
	public List<Task> getAllTasks() {
		if (taskService.findAll() != null)
			return taskService.findAll();
		else
			throw new NotFoundException("Error retrieving tasks!");

	}

	@DeleteMapping("/tasks/delete")
	public void deleteAllTasks() {
		taskService.deleteAll();
	}

	@GetMapping("/tasks/{teamId}")
	public List<Task> getTasksByTeamId(@PathVariable String teamId) {
		if (teamId != "" && teamId != null) {
			Optional<Team> team = teamService.findById(teamId);
			if (team.get() != null)
				return team.get().getTasks();
			else
				throw new NotFoundException("Task not found!");
		}
		return null;

	}

	@CrossOrigin(origins = "http://localhost:3000")
	@DeleteMapping("/tasks/delete/{taskId}/{teamId}")
	@ResponseStatus(code = HttpStatus.OK)
	public void deleteTasksById(@PathVariable String taskId,@PathVariable String teamId) {

		
		if (taskId != "" && taskId != null) {
			Optional<Team> team = teamService.findById(teamId);
			taskService.delete(taskId);
			System.out.println(team.get().getTasks().toString());
			team.get().removeTask(taskId);
			
			System.out.println(team.get().getTasks().toString());
			teamService.update(team.get());
			System.out.println(team.get().getTasks().toString());

		}

	}

	@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:8080" })
	@ResponseStatus(code = HttpStatus.OK)
	@PatchMapping("/tasks/update/{taskId}/{teamId}")
	public void updateTask(@PathVariable String taskId, @PathVariable String teamId,
			@Validated @RequestBody Task task) {
		Optional<Task> currentTask = taskService.findById(taskId);
		Optional<Team> currentTeam = teamService.findById(teamId);
		if (currentTask != null && currentTask.get() != null) {
			currentTask.get().setAccomplishment(task.getAccomplishment());
			currentTask.get().setPoints(task.getPoints());
			taskService.update(currentTask.get());
			
			if (currentTeam.get() != null) {
				currentTeam.get().getTask(taskId).setAccomplishment(task.getAccomplishment());
				currentTeam.get().getTask(taskId).setPoints(task.getPoints());
				currentTeam.get().calculateTotalPoints();
				
				teamService.update(currentTeam.get());
			}
		} else
			throw new NotFoundException("Task not found!");

	}

}