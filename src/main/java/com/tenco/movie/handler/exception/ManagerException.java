package com.tenco.movie.handler.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

// 내부 적인 클로가 플요 할떄
@Getter
public class ManagerException extends RuntimeException{
	
	private HttpStatus status;
	
	public ManagerException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}
	
}