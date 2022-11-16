package com.solera.team1.project.team1project.Service;

import java.util.List;
import java.util.Optional;

import com.solera.team1.project.team1project.model.Task;

public interface ITaskService {
	
	void create(Task task);
	Optional<Task> findById(String id);
	List<Task> findAll();
	void delete(String id);
	Task update(Task team);
	void deleteAll();
	List<Task> findByTeamId(String teamId);
}
