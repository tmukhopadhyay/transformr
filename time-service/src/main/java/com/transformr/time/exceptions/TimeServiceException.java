package com.transformr.time.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TimeServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TimeServiceException(String message) {
		super(message);
	}

	public TimeServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
