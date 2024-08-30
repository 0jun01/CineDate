package com.tenco.movie.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.tenco.movie.repository.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonNaming(value=PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class GoogleProfile {
	
	private String id;
	private String name;
	private String email;
	private boolean verifiedEmail;
	private String givenName;
	private String familyName;
	private String picture;
	private String laclae;
	
	public User googleUser() {
		return User.builder()
				.loginId(id)
				.name(name)
				.password(id)
				.email(email)
				.build();
	}
	
}
