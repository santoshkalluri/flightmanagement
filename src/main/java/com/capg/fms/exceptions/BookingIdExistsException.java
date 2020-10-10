package com.capg.fms.exceptions;

public class BookingIdExistsException extends RuntimeException{
	public BookingIdExistsException(String message) {
	       super(message);
	}
}