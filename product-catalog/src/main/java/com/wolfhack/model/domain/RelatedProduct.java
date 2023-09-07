package com.wolfhack.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
public class RelatedProduct implements Serializable, DomainModel {
	private Long id;
	private Long productId;
	private Long relatedProductId;
}