package com.reservation.exceptions;

public class InvalidReservationTime extends RuntimeException {
	public InvalidReservationTime(String message) {
		super(message);
	}

}
