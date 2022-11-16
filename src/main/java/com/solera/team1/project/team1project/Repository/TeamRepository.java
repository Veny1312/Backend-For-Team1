package com.solera.team1.project.team1project.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.solera.team1.project.team1project.model.Team;


@Repository
public interface TeamRepository extends MongoRepository<Team, String> {
	
	@Query("{name}:?0 ")
	public List<Team> findByName (String name);

}
	