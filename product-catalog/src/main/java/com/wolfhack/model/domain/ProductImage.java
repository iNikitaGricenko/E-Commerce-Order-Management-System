package com.wolfhack.model.domain;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;

@Data
public class ProductImage implements Serializable, DomainModel {
	private Long id;
	private Long productId;
	private String imageUrl;
	private String description;
}