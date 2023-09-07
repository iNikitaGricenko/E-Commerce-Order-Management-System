package com.wolfhack.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCart implements Serializable, DomainModel {
	private Long id;
	private Long userId;
	private LocalDate createdDate;
	private LocalDate updatedDate;
}