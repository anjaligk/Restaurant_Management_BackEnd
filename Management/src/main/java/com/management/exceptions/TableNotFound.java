package com.management.exceptions;

public class TableNotFound extends RuntimeException {
	public TableNotFound(String message) {
		super(message);
	}

}
