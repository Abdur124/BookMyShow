package com.lld.bms.exceptions;

public class AuditoriumNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public AuditoriumNotFoundException() {

	}

	public AuditoriumNotFoundException(String message) {
		super(message);
	}

}
