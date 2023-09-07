package com.wolfhack.cloud.model;

public record MailMessagePOJO(
		String to,
		String subject,
		String body
) {
}
