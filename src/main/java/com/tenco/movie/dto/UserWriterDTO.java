package com.tenco.movie.dto;

import java.sql.Date;

import com.tenco.movie.repository.model.UserWrite;

public class UserWriterDTO {

	private int id;
	private String loginId;
	private String name;
	private String password;
	private String email;
	private String phoneNum;
	private Date birthDay;
	private String gender;
	
	public UserWrite toUser() {
		return UserWrite.builder()
				.loginId(this.loginId)
				.name(this.name)
				.password(this.password)
				.email(this.email)
				.phoneNum(this.phoneNum)
				.birthDay(this.birthDay)
				.gender(this.gender)
				.build();
	}
	
}
