package com.wolfhack.model.domain;

import com.wolfhack.model.dto.ProductAddedDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInventory implements Serializable, DomainModel {
	private Long id;
	private Long productId;
	private String name;
	private long availableQuantity;
	private long reservedQuantity;
	private LocalDate createdDate;
	private LocalDate updatedDate;

	public void create(ProductAddedDTO productAddedDTO) {
		this.id = productAddedDTO.id();
		this.name = productAddedDTO.name();
		this.availableQuantity = productAddedDTO.availableQuantity();
		this.reservedQuantity = 0;
		this.createdDate = LocalDate.now();
	}
}