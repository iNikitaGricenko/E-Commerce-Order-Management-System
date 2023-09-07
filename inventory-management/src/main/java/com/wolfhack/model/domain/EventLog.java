package com.wolfhack.model.domain;

import com.wolfhack.model.LogType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventLog implements Serializable, DomainModel {
	private Long id;
	private Long inventoryId;
	private long quantityChange;
	private LogType logType;
	private LocalDate logDate;
	private String description;

	public void inventoryCreated(long inventoryId, long stockQuantity) {
		this.inventoryId = inventoryId;
		this.quantityChange = stockQuantity;
		this.logType = LogType.CREATED;
		this.logDate = LocalDate.now();
		this.description = "Product in inventory was added";
	}

	public void productAdded(long inventoryId, long stockQuantity) {
		this.inventoryId = inventoryId;
		this.quantityChange = stockQuantity;
		this.logType = LogType.UPDATED;
		this.logDate = LocalDate.now();
		this.description = "Product was added to inventory";
	}

	public void inventoryRemoved(Long inventoryId) {
		this.inventoryId = inventoryId;
		this.quantityChange = 0L;
		this.logType = LogType.REMOVED;
		this.logDate = LocalDate.now();
		this.description = "Inventory was removed";
	}
}