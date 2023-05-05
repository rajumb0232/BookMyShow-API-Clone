package edu.project.bookmyshow.exception;

@SuppressWarnings("serial")
public class NullObjectPassedException extends RuntimeException {
	private String message;

	public NullObjectPassedException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
