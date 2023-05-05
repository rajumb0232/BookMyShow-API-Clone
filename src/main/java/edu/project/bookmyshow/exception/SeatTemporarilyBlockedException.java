package edu.project.bookmyshow.exception;

@SuppressWarnings("serial")
public class SeatTemporarilyBlockedException extends RuntimeException {
	private String message;

	public SeatTemporarilyBlockedException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
