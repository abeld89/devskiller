package com.mango.customer.handler;

import com.mango.customer.handler.exceptions.SloganLimitException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class AppExceptionHandler {

	@ExceptionHandler(value = SloganLimitException.class)
	public ResponseEntity<Object> handleException(SloganLimitException applicationException){
		return new ResponseEntity<>("You have exceeded the slogan limit", HttpStatus.NOT_ACCEPTABLE);
	}
}
