package com.lld.bms.exceptions;

public class ShowSeatNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public ShowSeatNotFoundException() {

	}

	public ShowSeatNotFoundException(String message) {
		super(message);
	}

}
