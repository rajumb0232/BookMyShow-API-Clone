package edu.project.bookmyshow.exception;

@SuppressWarnings("serial")
public class TicketAlreadyCancelledException extends RuntimeException {
	private String message;

	public TicketAlreadyCancelledException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
