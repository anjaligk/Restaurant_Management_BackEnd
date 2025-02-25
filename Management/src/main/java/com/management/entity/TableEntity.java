package com.management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tables")
public class TableEntity {

	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tableId;

	@Column(nullable = false)
	private Integer capacity;

	public int getTableId() {
		return tableId;
	}

	public void setTableId(int tableID) {
		tableId = tableID;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public TableEntity(int tableID, Integer capacity) {
		super();
		tableId = tableID;
		this.capacity = capacity;
	}

	public TableEntity() {
		super();
	}

	@Override
	public String toString() {
		return "TableEntity [TableId=" + tableId + ", capacity=" + capacity + "]";
	}

}