package edu.project.bookmyshow.exception;

@SuppressWarnings("serial")
public class ShowsNotFoundInLocationException extends RuntimeException {
	private String message;

	public ShowsNotFoundInLocationException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
