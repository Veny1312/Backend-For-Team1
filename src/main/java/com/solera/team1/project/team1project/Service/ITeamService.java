package com.solera.team1.project.team1project.Service;

import java.util.List;
import java.util.Optional;

import com.solera.team1.project.team1project.model.Team;

public interface ITeamService {
	
	Team create(Team team);
	
	List<Team> findByName(String name);
	
	Optional<Team> findById(String id);
	
	List<Team> findAll();
	
	void delete(String id);
	
	Team update(Team team);
	
}
