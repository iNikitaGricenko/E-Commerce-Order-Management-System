package com.wolfhack.cloud.model;

import java.io.Serializable;
import java.time.LocalDate;

public record UserRegisteredNotificationDTO(String email, String firstName, String lastName,
                                            LocalDate birthDate, String address) implements Serializable {

}