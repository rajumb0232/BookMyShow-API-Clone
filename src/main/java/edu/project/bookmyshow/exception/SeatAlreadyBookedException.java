package edu.project.bookmyshow.exception;

@SuppressWarnings("serial")
public class SeatAlreadyBookedException extends RuntimeException {
	private String message;

	public SeatAlreadyBookedException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
