package edu.project.bookmyshow.exception;

@SuppressWarnings("serial")
public class ShowNotFoundByIdException extends RuntimeException {
	private String message;

	public ShowNotFoundByIdException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
