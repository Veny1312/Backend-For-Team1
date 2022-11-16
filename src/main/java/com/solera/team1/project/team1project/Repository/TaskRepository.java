package com.solera.team1.project.team1project.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.solera.team1.project.team1project.model.Task;
@Repository
public interface TaskRepository extends MongoRepository<Task, String>{
	
	@Query("{'title'}:?0 ")
	public List<Task> findByName();
	@Query("{'teamId'}:?0 ")
	public List<Task> findByTeamId();
}
