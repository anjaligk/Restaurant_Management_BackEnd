package com.reservation.dto;

import lombok.Data;

@Data
public class TableDTO {
	private int tableId;
	private Integer capacity;

	public int getTableId() {
		return tableId;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public TableDTO() {
	}

	public TableDTO(int tableId, Integer capacity, Boolean isAvailable) {
		this.tableId = tableId;
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return "TableDTO{" + "tableId=" + tableId + ", capacity=" + capacity +  '}';
	}
}