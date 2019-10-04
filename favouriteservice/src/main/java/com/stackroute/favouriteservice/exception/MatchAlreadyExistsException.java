package com.stackroute.favouriteservice.exception;

public class MatchAlreadyExistsException extends Exception {

	private String message;

	public MatchAlreadyExistsException(String message) {
		super(message);
		this.message = message;

	}

	@Override
	public String toString() {

		return "MatchAlreadyExistsException: [" + message + "]";
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
