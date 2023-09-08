package com.wolfhack.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {

	PACKAGING, PENDING, DELIVERED, RETURNED;

}
