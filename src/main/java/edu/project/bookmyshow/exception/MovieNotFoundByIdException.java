package edu.project.bookmyshow.exception;

@SuppressWarnings("serial")
public class MovieNotFoundByIdException extends RuntimeException {
	
	private String message;

	public MovieNotFoundByIdException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
