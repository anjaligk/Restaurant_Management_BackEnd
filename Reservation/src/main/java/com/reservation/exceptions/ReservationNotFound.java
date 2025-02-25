package com.reservation.exceptions;

public class ReservationNotFound extends RuntimeException {

	public ReservationNotFound(String message) {
		super(message);
	}
}
