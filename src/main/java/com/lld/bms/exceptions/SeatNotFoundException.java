package com.lld.bms.exceptions;

public class SeatNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public SeatNotFoundException() {

	}

	public SeatNotFoundException(String message) {
		super(message);
	}

}
