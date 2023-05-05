package edu.project.bookmyshow.exception;
@SuppressWarnings("serial")
public class TheaterNotFoundByIdException extends RuntimeException {
	private String message;

	public TheaterNotFoundByIdException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
