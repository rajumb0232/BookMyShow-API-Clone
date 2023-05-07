package edu.project.bookmyshow.exception;

@SuppressWarnings("serial")
public class TicketNotFoundByIdException extends RuntimeException {
	private String message;

	public TicketNotFoundByIdException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
