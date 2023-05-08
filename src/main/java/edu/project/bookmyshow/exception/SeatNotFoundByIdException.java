package edu.project.bookmyshow.exception;

@SuppressWarnings("serial")
public class SeatNotFoundByIdException extends RuntimeException {
	private String message;

	public SeatNotFoundByIdException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
