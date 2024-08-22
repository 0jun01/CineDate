package com.tenco.movie.repository.model;

import java.sql.Date;

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
	
	private String loginId;
	private String name;
	private String password;
	private String email;
	private int phoneNum;
	private Date birthDay;
	private boolean gender;

}
