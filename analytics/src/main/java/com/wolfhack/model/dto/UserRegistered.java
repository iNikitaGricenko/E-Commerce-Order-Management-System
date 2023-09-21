package com.wolfhack.model.dto;

import java.io.Serializable;
import java.time.LocalDate;

public record UserRegistered(String email,
                             String firstName,
                             String lastName,
                             LocalDate birthDate,
                             String address) implements Serializable {

}