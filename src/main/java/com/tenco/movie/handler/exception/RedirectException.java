package com.tenco.movie.handler.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

/**
 * 잘못된 요청, 페이지 이동 오류
 */
@Getter
public class RedirectException extends RuntimeException{

	private HttpStatus status;
	
	public RedirectException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}
	
}
