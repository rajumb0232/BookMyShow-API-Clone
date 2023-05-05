package edu.project.bookmyshow.exception;

@SuppressWarnings("serial")
public class ScreenNotFoundByIdException extends RuntimeException {
	
	private String message;

	public ScreenNotFoundByIdException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
