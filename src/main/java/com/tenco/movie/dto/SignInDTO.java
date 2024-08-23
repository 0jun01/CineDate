package com.tenco.movie.dto;

import com.tenco.movie.repository.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SignInDTO {
	
	private String loginId;
	private String password;

	public User toUser() {
		return User.builder()
				.loginId(this.getLoginId())
				.password(this.getPassword())
				.build();
	}
	
}
