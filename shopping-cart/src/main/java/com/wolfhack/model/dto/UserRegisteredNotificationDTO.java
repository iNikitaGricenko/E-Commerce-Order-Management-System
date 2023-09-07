package com.wolfhack.model.dto;

import java.io.Serializable;
import java.time.LocalDate;

public record UserRegisteredNotificationDTO(Long id, String email, String firstName, String lastName,
                                            LocalDate birthDate, String address) implements Serializable {

}