package com.management.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.management.dto.ReservationDTO;
import com.management.entity.TableEntity;
import com.management.service.TableService;

@RestController
@RequestMapping("/manageTable")
public class TableController {

	private final TableService tableService;

    @Autowired
    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

	@PostMapping("/add")
	public TableEntity addTable(@RequestBody TableEntity table) {
		return tableService.addTable(table);
	}

	@PutMapping("/change")
	public TableEntity updateTable(@RequestBody TableEntity table) {
		return tableService.updateTable(table);
	}

	@DeleteMapping("/delete/{TableId}")
	public void deleteTable(@PathVariable("TableId") int tableId) {
	tableService.deleteTable(tableId);
	}

	@GetMapping("/getall")
	public List<TableEntity> getAllTable() {
		return tableService.getAllTable();
	}

	@GetMapping("/getAllReservations")
	public List<ReservationDTO> getAllReservation() {
		return tableService.getAllReservation();
	}

	@GetMapping("/getReservationByDateAndTime")
	public List<ReservationDTO> getReservationsForDateTime(@RequestParam("date") LocalDate date,
			@RequestParam("time") LocalTime time) {
		return tableService.getReservationsForDateTime(date, time);
	}
}
