package com.lld.bms.exceptions;

public class ShowSeatsNotAvailableException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ShowSeatsNotAvailableException() {

	}

	public ShowSeatsNotAvailableException(String message) {
		super(message);
	}

}
