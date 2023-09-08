package com.wolfhack.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LogType {

	ADDED, CREATED, UPDATED, REMOVED;

}
