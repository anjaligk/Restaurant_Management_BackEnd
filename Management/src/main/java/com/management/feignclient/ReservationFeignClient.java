package com.management.feignclient;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.management.dto.ReservationDTO;

@FeignClient(name = "RESERVATION", path = "/reservation")
public interface ReservationFeignClient {

	@GetMapping("/getall")
	public List<ReservationDTO> getAllReservation();
	
	@GetMapping("/findByDateAndTime")
	public List<ReservationDTO> getAllReservationByDateandTime(@RequestParam("date") LocalDate date,
					@RequestParam("time") LocalTime time );

	
}
