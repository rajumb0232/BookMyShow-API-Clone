package edu.project.bookmyshow.exception;

@SuppressWarnings("serial")
public class ShowOnGoingOrClosedException extends RuntimeException {
	private String message;

	public ShowOnGoingOrClosedException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
