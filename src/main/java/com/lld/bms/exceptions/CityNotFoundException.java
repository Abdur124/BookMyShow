package com.lld.bms.exceptions;

public class CityNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CityNotFoundException() {

	}

	public CityNotFoundException(String message) {
		super(message);
	}

}
