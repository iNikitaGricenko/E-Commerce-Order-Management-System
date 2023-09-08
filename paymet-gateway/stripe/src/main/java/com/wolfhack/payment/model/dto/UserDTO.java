package com.wolfhack.payment.model.dto;

import lombok.Value;

import java.io.Serializable;

@Value
public class UserDTO implements Serializable {

	Long id;
	String email;
	String phoneNumber;

}
