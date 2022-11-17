package com.solera.team1.project.team1project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@Document("teams")
public class Team {

	@Id
	private String id;
	private String name;
	private long totalPoints;
	@DBRef
	@OneToMany(mappedBy = "team")
	private List<Task> tasks;

	public Team() {
		this.tasks = new ArrayList<Task>();
	}

	public void addTask(Task task) {
		this.tasks.add(task);
		this.calculateTotalPoints();
	}
	public Task getTask(String taskId) {
		System.out.println(taskId);
		System.out.println(this.tasks.toString());
	for(Task task : tasks) {
		if(task.getId()==taskId) {
			System.out.println(task);
			return task;
		}
	}
	return null;
	
	}

	public Team(String name) {
		super();

		this.name = name;

		this.tasks = new ArrayList<Task>();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}
public void removeTask(String id) {
	this.tasks=tasks.stream().filter(e->e.getId()!=id).collect(Collectors.toList());
	this.calculateTotalPoints();
}
	public long getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(long totalPoints) {
		this.totalPoints = totalPoints;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = new ArrayList<Task>(tasks);
		this.calculateTotalPoints();
	}

	public void calculateTotalPoints() {
		this.totalPoints = 0;
		this.tasks.forEach(task -> {
			this.totalPoints += task.getPoints();
		});
	}

	public Team(List<Task> tasks) {
		super();

		this.tasks = tasks;
		this.calculateTotalPoints();
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", totalPoints=" + totalPoints + ", tasks=" + tasks.toString() + "]";
	}

}