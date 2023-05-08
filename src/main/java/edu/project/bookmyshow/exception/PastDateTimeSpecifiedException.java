package edu.project.bookmyshow.exception;

@SuppressWarnings("serial")
public class PastDateTimeSpecifiedException extends RuntimeException {
	private String message;

	public PastDateTimeSpecifiedException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
