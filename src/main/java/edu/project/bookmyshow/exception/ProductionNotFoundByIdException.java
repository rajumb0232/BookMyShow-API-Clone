package edu.project.bookmyshow.exception;
@SuppressWarnings("serial")
public class ProductionNotFoundByIdException extends RuntimeException {
	private String message;


	public ProductionNotFoundByIdException(String message) {
		super();
		this.message = message;
	}


	public String getMessage() {
		return message;
	}

}
