package edu.project.bookmyshow.exception;

@SuppressWarnings("serial")
public class ShowAlreadyExpiredException extends RuntimeException {
	private String message;

	public ShowAlreadyExpiredException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
