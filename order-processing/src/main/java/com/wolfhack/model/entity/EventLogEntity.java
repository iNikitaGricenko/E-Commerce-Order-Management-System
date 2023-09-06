package com.wolfhack.model.entity;

import java.time.LocalDate;

public class EventLogEntity {

	private Long id;

	private Long orderId;

	private String statusChange;

	private String logType;

	private LocalDate logDate;

	private String description;

}
