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
	private Long productId;
	private long quantityChange;
	private LogType logType;
	private LocalDate logDate;
	private String description;
}