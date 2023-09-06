package com.wolfhack.model.entity;

import java.util.List;

public class ProductEntity {

	private Long id;

	private String name;

	private String description;

	private List<String> images;

	private boolean availability;

	private List<ProductSpecificationEntity> productSpecifications;

}
