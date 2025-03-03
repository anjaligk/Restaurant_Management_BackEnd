package com.reservation.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reservation.dto.TableDTO;
import com.reservation.entity.Reservation;
import com.reservation.exceptions.ReservationNotFound;
import com.reservation.service.ReservationService;

@RestController
@RequestMapping("/reservation") 
public class ReservationController {

	private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

	@GetMapping("/available-tables")
	public List<TableDTO> getAvailableTables(@RequestParam("date") LocalDate date,
			@RequestParam("time") LocalTime time) {
		return reservationService.getAvailableTablesForDateTime(date, time);
	}

	@PostMapping("/book")
	public Reservation bookTable(@RequestBody Reservation reservation) {
		return reservationService.bookTable(reservation);
	}

	@DeleteMapping("/cancel/{reservationId}")
	public void cancelReservation(@PathVariable int reservationId) {
		reservationService.cancelReservation(reservationId);
	}

	@GetMapping("/getById/{Id}")
	public Reservation getResevationById(@PathVariable("Id") int id) throws ReservationNotFound {
		return reservationService.getResevationById(id);
	}

	@GetMapping("/getall")
	public List<Reservation> getAllReservation() {
		return reservationService.getAllReservation();
	}
	
	@GetMapping("/findByDateAndTime")
	public List<Reservation> getAllReservationByDateandTime(@RequestParam("date") LocalDate date,
					@RequestParam("time") LocalTime time )
	{
		// Debug log
	    System.out.println("ReservationController - Date: " + date + ", Time: " + time);
		return reservationService.getReservationsForDateTime(date,time);
	}
	
	@GetMapping("/getByCustomerId/{customerId}")
	public List<Reservation> getReservationsByCustomerId(@PathVariable int customerId) {
	    return reservationService.getReservationsByCustomerId(customerId);
	}

}
