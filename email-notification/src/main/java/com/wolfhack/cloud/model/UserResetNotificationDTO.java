package com.wolfhack.cloud.model;

import java.io.Serializable;

public record UserResetNotificationDTO(Long id, String email, String username, String resetToken) implements Serializable {

}