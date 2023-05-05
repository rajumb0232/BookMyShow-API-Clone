package edu.project.bookmyshow.exception;

@SuppressWarnings("serial")
public class CustomerNotFoundByIdException extends RuntimeException {

	private String message;

	public CustomerNotFoundByIdException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
