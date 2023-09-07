package com.wolfhack.model.domain;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;

@Data
public class Category implements Serializable, DomainModel {
	private Long id;
	private String name;
}