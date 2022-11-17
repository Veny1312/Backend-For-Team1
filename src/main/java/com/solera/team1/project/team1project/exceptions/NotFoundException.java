package com.solera.team1.project.team1project.exceptions;

public class NotFoundException extends RuntimeException {
private String message;

public NotFoundException(String message) {
	super(message);
	
}

}
