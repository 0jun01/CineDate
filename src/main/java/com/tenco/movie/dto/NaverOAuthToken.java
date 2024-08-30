package com.tenco.movie.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonNaming(value=PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NaverOAuthToken {

	private String accessToken;
	private String refreshToken;
	private String tokenType;
	private Integer expiresIn;
	private String error;
	private String errorDescription;
	
}
