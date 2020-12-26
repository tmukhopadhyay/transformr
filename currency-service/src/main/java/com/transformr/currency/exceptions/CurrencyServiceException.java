package com.transformr.currency.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CurrencyServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CurrencyServiceException(String message) {
		super(message);
	}

	public CurrencyServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
