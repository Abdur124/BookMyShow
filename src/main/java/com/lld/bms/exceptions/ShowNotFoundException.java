package com.lld.bms.exceptions;

public class ShowNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public ShowNotFoundException() {

	}

	public ShowNotFoundException(String message) {
		super(message);
	}

}
