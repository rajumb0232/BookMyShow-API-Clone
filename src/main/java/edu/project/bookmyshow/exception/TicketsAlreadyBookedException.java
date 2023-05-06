package edu.project.bookmyshow.exception;

@SuppressWarnings("serial")
public class TicketsAlreadyBookedException extends RuntimeException {
	private String message;

	public TicketsAlreadyBookedException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
