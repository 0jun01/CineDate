package com.tenco.movie.handler.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

/**
 * 인증되지 않은 사용자, 권한이 없는 사용자 오류
 */
@Getter
public class UnAuthorizedException extends RuntimeException{

	private HttpStatus status;
	
	public UnAuthorizedException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}
	
}
