package com.lld.bms.exceptions;

public class TicketNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public TicketNotFoundException() {

	}

	public TicketNotFoundException(String message) {
		super(message);
	}

}
