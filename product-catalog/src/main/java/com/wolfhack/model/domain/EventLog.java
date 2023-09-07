package com.wolfhack.model.domain;

import com.wolfhack.model.LogType;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class EventLog implements Serializable, DomainModel {
	private Long id;
	private Long productId;
	private long quantityChange;
	private LogType logType;
	private LocalDate logDate;
	private String description;

	public void productCreated(long productId, long stockQuantity) {
		this.productId = productId;
		this.quantityChange = stockQuantity;
		this.logType = LogType.CREATED;
		this.logDate = LocalDate.now();
		this.description = "Product was added";
	}

	public void productVariantCreated(long productId, long stockQuantity) {
		this.productId = productId;
		this.quantityChange = stockQuantity;
		this.logType = LogType.UPDATED;
		this.logDate = LocalDate.now();
		this.description = "Product variant was added";
	}

	public void productRemoved(Long productId) {
		this.productId = productId;
		this.quantityChange = 0L;
		this.logType = LogType.REMOVED;
		this.logDate = LocalDate.now();
		this.description = "Product was removed";
	}
}