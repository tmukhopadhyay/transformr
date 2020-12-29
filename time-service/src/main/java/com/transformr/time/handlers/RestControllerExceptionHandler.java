package com.transformr.time.handlers;

import java.util.ArrayList;
import java.util.List;

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

import com.transformr.time.dto.TimeZoneDto;
import com.transformr.time.exceptions.TimeServiceException;

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
		TimeServiceException.class
	})
	public ResponseEntity<List<TimeZoneDto>> handleApplicationException(Exception ex) {
		ex.printStackTrace();
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<List<TimeZoneDto>> handleDefaultException(Exception ex) {
		ex.printStackTrace();
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
