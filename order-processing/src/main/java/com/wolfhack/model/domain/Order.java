package com.wolfhack.model.domain;

import com.wolfhack.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable, DomainModel {
	private Long id;
	private Long userId;
	private OrderStatus orderStatus;
	private long totalAmount;
	private LocalDate orderDate;
	private Long paymentId;
	private String shippingAddress;
	private LocalDate createdDate;
	private LocalDate updatedDate;
}