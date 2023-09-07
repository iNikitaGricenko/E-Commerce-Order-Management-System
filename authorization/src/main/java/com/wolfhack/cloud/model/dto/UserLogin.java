package com.wolfhack.cloud.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserLogin(String username, String password)  implements Serializable {
}
