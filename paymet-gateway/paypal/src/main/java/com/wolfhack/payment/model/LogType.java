package com.wolfhack.payment.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LogType {

	REQUEST, RESPONSE, ERROR;

}
