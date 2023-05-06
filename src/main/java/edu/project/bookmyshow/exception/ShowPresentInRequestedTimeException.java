package edu.project.bookmyshow.exception;

@SuppressWarnings("serial")
public class ShowPresentInRequestedTimeException extends RuntimeException {
	private String message;

	public ShowPresentInRequestedTimeException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
