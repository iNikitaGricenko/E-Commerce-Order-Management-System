package com.wolfhack.cloud.service;

import com.wolfhack.cloud.model.MailMessagePOJO;

public interface EmailSender {

	void send(MailMessagePOJO mailMessagePOJO);

}
