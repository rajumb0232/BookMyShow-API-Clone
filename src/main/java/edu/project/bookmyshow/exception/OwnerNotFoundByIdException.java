package edu.project.bookmyshow.exception;

@SuppressWarnings("serial")
public class OwnerNotFoundByIdException extends RuntimeException {
	private String message;

	public OwnerNotFoundByIdException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
