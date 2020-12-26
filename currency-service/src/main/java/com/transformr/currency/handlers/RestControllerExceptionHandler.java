package com.transformr.currency.handlers;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.transformr.currency.dto.ExchangeRateDto;
import com.transformr.currency.exceptions.CurrencyServiceException;

@RestControllerAdvice
public class RestControllerExceptionHandler {

	@ExceptionHandler({
		HttpRequestMethodNotSupportedException.class,
		HttpMediaTypeNotSupportedException.class,
		HttpMediaTypeNotAcceptableException.class,
		MissingPathVariableException.class,
		MissingServletRequestParameterException.class,
		ServletRequestBindingException.class,
		ConversionNotSupportedException.class,
		TypeMismatchException.class,
		MethodArgumentNotValidException.class,
		MissingServletRequestPartException.class,
		NoHandlerFoundException.class,
		CurrencyServiceException.class
	})
	public ResponseEntity<ExchangeRateDto> handleApplicationException(Exception ex) {
		ex.printStackTrace();
		ExchangeRateDto exchangeRate = new ExchangeRateDto();
		return new ResponseEntity<>(exchangeRate, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExchangeRateDto> handleDefaultException(Exception ex) {
		ex.printStackTrace();
		ExchangeRateDto exchangeRate = new ExchangeRateDto();
		return new ResponseEntity<>(exchangeRate, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
