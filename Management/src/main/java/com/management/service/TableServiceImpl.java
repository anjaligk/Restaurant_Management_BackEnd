package com.management.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.management.dto.ReservationDTO;
import com.management.entity.TableEntity;
import com.management.feignclient.ReservationFeignClient;
import com.management.repository.TableRepository;

@Service
@Transactional
public class TableServiceImpl implements TableService {

	private final TableRepository tableRepository;
    private final ReservationFeignClient reservationFeignClient;

    @Autowired
    public TableServiceImpl(TableRepository tableRepository, ReservationFeignClient reservationFeignClient) {
        this.tableRepository = tableRepository;
        this.reservationFeignClient = reservationFeignClient;
    }

	@Override
	public TableEntity addTable(TableEntity tableEntity) {
		return tableRepository.save(tableEntity);
	}

	@Override
	public TableEntity updateTable(TableEntity tableEntity) {
		return tableRepository.save(tableEntity);
	}

	@Override
	public void deleteTable(int tableId) {
		tableRepository.deleteById(tableId);
	}

	@Override
	public List<TableEntity> getAllTable() {
		return tableRepository.findAll();
	}

	@Override
	public List<ReservationDTO> getAllReservation() {
		return reservationFeignClient.getAllReservation();
	}
	
	@Override
	public List<ReservationDTO> getReservationsForDateTime(LocalDate date, LocalTime time) {
		return reservationFeignClient.getAllReservationByDateandTime(date, time);
	}

}
