package com.tenco.movie.dto;

import java.sql.Date;

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
public class SignUpDTO {
	
	private String loginId;
	private String name;
	private String password;
	private String email;
	private int phoneNum;
	private int year;
	private int month;
	private int day;
	private String gender;
	
	// 2단계 로직 - User Object
	public User toUser() {
		return User.builder()
				.loginId(this.getLoginId())
				.name(this.getName())
				.password(this.getPassword())
				.email(this.getEmail())
				.phoneNum(this.getPhoneNum())
				.birthDay(Date.valueOf(year + "-" + month + "-" + day))
				.gender(this.getGender())
				.build();
	}
	
}
