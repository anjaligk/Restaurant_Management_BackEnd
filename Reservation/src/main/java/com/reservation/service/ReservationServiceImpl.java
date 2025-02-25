package com.reservation.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import com.reservation.dto.TableDTO;
import com.reservation.entity.Reservation;
import com.reservation.exceptions.InvalidReservationTime;
import com.reservation.exceptions.ReservationNotFound;
import com.reservation.feignclient.TableFeignClient;
import com.reservation.repository.ReservationRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

	private final ReservationRepository reservationRepository;
    private final TableFeignClient tableFeignClient;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, TableFeignClient tableFeignClient) {
        this.reservationRepository = reservationRepository;
        this.tableFeignClient = tableFeignClient;
    }

	private static final List<LocalTime> ALLOWED_TIMES = Arrays.asList(LocalTime.of(19, 0), LocalTime.of(20, 0));

	public Reservation bookTable(Reservation reservation) {

		LocalDate date = reservation.getReservationDate();
		LocalTime time = reservation.getReservationTime();

		// Validate date
		LocalDate tomorrow = LocalDate.now().plusDays(1);
		LocalDate oneWeekLater = LocalDate.now().plusDays(7);
		if (date.isBefore(tomorrow) || date.isAfter(oneWeekLater)) {
			throw new IllegalArgumentException("Date must be between tomorrow and one week from today.");
		}

		// Validate time
		if (!ALLOWED_TIMES.contains(time)) {
			throw new InvalidReservationTime("Time must be either 19:00 or 20:00.");
		}

		// Implementation to check, create reservation, and update table status
		return reservationRepository.save(reservation);
	}

	public void cancelReservation(int reservationId) {
		Reservation reservation = reservationRepository.findById(reservationId).orElseThrow();
		reservationRepository.delete(reservation);
	}

	@Override
	public Reservation getResevationById(int id) throws ReservationNotFound {
		Optional<Reservation> optional = reservationRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else
			throw new ReservationNotFound("Reservation with the given Id is not found !!!!!");
	}

	@Override
	public List<Reservation> getAllReservation() {
		return reservationRepository.findAll();
	}
	
	public List<Reservation> getReservationsForDateTime(LocalDate date, LocalTime time) {
		return reservationRepository.findByReservationDateAndReservationTime( date, time);
	}
	

	@Override
	public List<TableDTO> getAvailableTablesForDateTime(LocalDate date, LocalTime time) {
		List<TableDTO> allTables = tableFeignClient.getAllTable();

		List<Reservation> reservations = reservationRepository.findByReservationDateAndReservationTime(date, time);

		List<Integer> bookedTableIds = reservations.stream().map(Reservation::getTableId).collect(Collectors.toList());

		// Filter out booked tables
		return allTables.stream().filter(table -> !bookedTableIds.contains(table.getTableId()))
				.collect(Collectors.toList());
	}

}
