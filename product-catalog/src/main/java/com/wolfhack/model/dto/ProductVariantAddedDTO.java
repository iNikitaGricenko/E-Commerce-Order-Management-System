package com.wolfhack.model.dto;

public record ProductVariantAddedDTO(Long id, Long productId, String name, long availableQuantity) {
}
