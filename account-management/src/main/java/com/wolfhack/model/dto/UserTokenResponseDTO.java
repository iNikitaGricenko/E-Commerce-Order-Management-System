package com.wolfhack.model.dto;

import lombok.Value;

import java.io.Serializable;

@Value
public class UserTokenResponseDTO implements Serializable {

	Long id;
	String username;

}
