package edu.project.bookmyshow.exception;

@SuppressWarnings("serial")
public class TicketAlreadyExpiredException extends RuntimeException {
	private String message;

	public TicketAlreadyExpiredException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
