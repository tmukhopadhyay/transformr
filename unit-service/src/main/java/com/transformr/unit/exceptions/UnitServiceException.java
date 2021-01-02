package com.transformr.unit.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UnitServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnitServiceException(String message) {
		super(message);
	}

	public UnitServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
