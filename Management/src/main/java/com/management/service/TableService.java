package com.management.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.management.dto.ReservationDTO;
import com.management.entity.TableEntity;

public interface TableService {

	TableEntity addTable(TableEntity tableEntity);

	TableEntity updateTable(TableEntity tableEntity);

	void deleteTable(int tableId);

	public List<TableEntity> getAllTable();

	List<ReservationDTO> getAllReservation();
	
	List<ReservationDTO> getReservationsForDateTime(LocalDate date, LocalTime time);
	
}
