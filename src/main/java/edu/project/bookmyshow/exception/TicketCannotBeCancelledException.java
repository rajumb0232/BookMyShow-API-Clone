package edu.project.bookmyshow.exception;

@SuppressWarnings("serial")
public class TicketCannotBeCancelledException extends RuntimeException {
	private String message;

	public TicketCannotBeCancelledException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
