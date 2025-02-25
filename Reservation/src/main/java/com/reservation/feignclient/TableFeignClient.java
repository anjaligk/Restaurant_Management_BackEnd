package com.reservation.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.reservation.dto.TableDTO;

@FeignClient(name = "MANAGEMENT", path = "/manageTable")
public interface TableFeignClient {

	@GetMapping("/getall")
	public List<TableDTO> getAllTable();
}
