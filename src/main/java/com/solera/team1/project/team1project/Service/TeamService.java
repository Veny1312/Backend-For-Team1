package com.solera.team1.project.team1project.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solera.team1.project.team1project.Repository.TeamRepository;
import com.solera.team1.project.team1project.model.Team;

@Service
public class TeamService implements ITeamService {
	
	@Autowired
	public TeamRepository teamRepository;

	@Override
	public Team create(Team team) {
		return teamRepository.save(team);
	}

	@Override
	public List<Team> findByName(String name) {
		return teamRepository.findByName(name);
	}

	@Override
	public List<Team> findAll() {
		return teamRepository.findAll();
	}

	@Override
	public void delete(String id) {
		teamRepository.deleteById(id);
	}

	@Override
	public Team update(Team team) {
		return teamRepository.save(team);
	}

	@Override
	public Optional<Team> findById(String id) {
		return teamRepository.findById(id) ;
	}

	public TeamRepository getTeamRepository() {
		return teamRepository;
	}

}
