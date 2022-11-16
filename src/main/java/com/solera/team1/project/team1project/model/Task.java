package com.solera.team1.project.team1project.model;


import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.mongodb.lang.NonNull;
import lombok.NoArgsConstructor;
import lombok.ToString;
@NoArgsConstructor
@ToString
@Document("task")
public class Task {
@Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private String id;
  private String accomplishment;
  private long points;

public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getAccomplishment() {
	return accomplishment;
}
public void setAccomplishment(String accomplishment) {
	this.accomplishment = accomplishment;
}




public Task(String accomplishment, long points, Team team) {
	super();
	this.accomplishment = accomplishment;
	this.points = points;
	
}
public Task() {
	super();
}

public long getPoints() {
	return points;
}
public void setPoints(long points) {
	this.points = points;
}



  
}