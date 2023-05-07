package edu.project.bookmyshow.exception;

@SuppressWarnings("serial")
public class ShowsNotFoundForMovieException extends RuntimeException {
	private String message;

	public ShowsNotFoundForMovieException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
