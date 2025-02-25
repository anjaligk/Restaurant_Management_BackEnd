package com.reservation.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.reservation.dto.TableDTO;
import com.reservation.entity.Reservation;
import com.reservation.exceptions.ReservationNotFound;

public interface ReservationService {

	public Reservation bookTable(Reservation reservation);

	Reservation getResevationById(int id) throws ReservationNotFound;

	List<Reservation> getAllReservation();

	public void cancelReservation(int reservationId);

	List<TableDTO> getAvailableTablesForDateTime(LocalDate date, LocalTime time);
	
	public List<Reservation> getReservationsForDateTime(LocalDate date, LocalTime time);
}
