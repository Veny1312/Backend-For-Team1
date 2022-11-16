package com.solera.team1.project.team1project.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solera.team1.project.team1project.Repository.TaskRepository;
import com.solera.team1.project.team1project.model.Task;

@Service
public class TaskService implements ITaskService {
	@Autowired
	private TaskRepository taskRepository;

	@Override
	public void create(Task task) {
		taskRepository.save(task);

	}

	@Override
	public Optional<Task> findById(String id) {
		return taskRepository.findById(id);
	}

	@Override
	public List<Task> findAll() {
		return taskRepository.findAll();
	}

	@Override
	public void delete(String id) {
		taskRepository.deleteById(id);

	}

	@Override
	public Task update(Task team) {
		return taskRepository.save(team);
	}

	@Override
	public void deleteAll() {
		taskRepository.deleteAll();
	}

	@Override
	public List<Task> findByTeamId(String teamId) {

		return taskRepository.findByTeamId();

	}

	public TaskRepository getTaskRepository() {
		return taskRepository;
	}

	
}
