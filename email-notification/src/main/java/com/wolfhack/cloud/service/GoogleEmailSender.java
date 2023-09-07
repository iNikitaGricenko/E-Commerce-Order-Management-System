package com.wolfhack.cloud.service;

import com.wolfhack.cloud.model.MailMessagePOJO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoogleEmailSender implements EmailSender {

	private final JavaMailSender mailSender;

	@Value("${mail.send.from:noreply@wolfhack.com}")
	private String mailFrom;

	@Override
	public void send(MailMessagePOJO messagePOJ) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(mailFrom);
		message.setTo(messagePOJ.to());
		message.setSubject(messagePOJ.subject());
		message.setText(messagePOJ.body());

		mailSender.send(message);
	}
}
