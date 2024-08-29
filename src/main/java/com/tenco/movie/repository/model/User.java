package com.tenco.movie.repository.model;

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
public class User {
	
	private int id;
	private String loginId;
	private String name;
	private String password;
	private String email;
	private String phoneNum;
	private String birthDay;
	private String gender;
	
}
