package com.stackroute.favouriteservice.exception;

/**
 * 
 * @author Altap
 *
 */
public class MatchNotFoundException extends Exception {

	private String message;
	
	public MatchNotFoundException(String message) {
		super(message);
		this.message = message;
		
	}
	@Override
	public String toString() {
		
		return "MatchNotFoundException: [" + message + "]";
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
