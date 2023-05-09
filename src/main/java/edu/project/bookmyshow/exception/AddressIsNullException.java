package edu.project.bookmyshow.exception;

@SuppressWarnings("serial")
public class AddressIsNullException extends RuntimeException {
	private String message;

	public AddressIsNullException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
