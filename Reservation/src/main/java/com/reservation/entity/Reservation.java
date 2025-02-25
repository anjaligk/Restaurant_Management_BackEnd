package com.reservation.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="reservations")
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reservationId;
	
	@Column(nullable = false)
	private int customerId; 
	
	@Column(nullable = false)
	private int tableId;
	
	@Column(nullable = false)
	@Future(message = "Reservation date must be in the future")
	private LocalDate reservationDate;
	
	@Column(nullable = false)
	private LocalTime reservationTime;
	
	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getTableId() {
		return tableId;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	public LocalDate getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(LocalDate reservationDate) {
		this.reservationDate = reservationDate;
	}

	public LocalTime getReservationTime() {
		return reservationTime;
	}

	public void setReservationTime(LocalTime reservationTime) {
		this.reservationTime = reservationTime;
	}

	@Override
	public String toString() {
		return "Reservation [reservationId=" + reservationId + ", customerId=" + customerId + ", tableId=" + tableId
				+ ", reservationDate=" + reservationDate + ", reservationTime=" + reservationTime + "]";
	}

	public Reservation(int reservationId, int customerId, int tableId, LocalDate reservationDate,
			LocalTime reservationTime) {
		super();
		this.reservationId = reservationId;
		this.customerId = customerId;
		this.tableId = tableId;
		this.reservationDate = reservationDate;
		this.reservationTime = reservationTime;
	}

	public Reservation() {
		super();
	}

}
