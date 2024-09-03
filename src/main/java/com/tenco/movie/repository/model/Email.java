package com.tenco.movie.repository.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Email {
	
	@jakarta.validation.constraints.Email
	@NotEmpty(message = "이메일을 입력해 주세요.")
	private String email;
	
}
