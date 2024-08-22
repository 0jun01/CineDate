package com.tenco.movie.handler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import lombok.Getter;

/**
 * 데이터 전송 오류
 */
@Getter
public class DataDeliveryException extends RuntimeException {

	private HttpStatus status;

	public DataDeliveryException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}

	
}
