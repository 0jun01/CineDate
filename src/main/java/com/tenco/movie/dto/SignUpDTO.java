package com.tenco.movie.dto;

import org.springframework.web.multipart.MultipartFile;

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
	private String enterPassword;
	private String email;
	private String phoneNum;
	private String year;
	private String month;
	private String day;
	private String gender;
	
	private String birthday;
	
	private String username;
	private String fullname;
	private MultipartFile mFile;
	private String originFileName;
	private String uploadFileName;
	
	// 2단계 로직 - User Object
	public User toUser() {
		return User.builder()
				.loginId(this.loginId)
				.name(this.name)
				.password(this.password)
				.email(this.email)
				.phoneNum(this.phoneNum)
				.birthDay(year + "-" + month + "-" + day)
				.gender(this.gender)
				.build();
	}
	
	public User kakaoUser() {
		return User.builder()
				.loginId(this.loginId)
				.name(this.username)
				.password(this.getPassword())
				.email(this.email)
				.fullname(this.getFullname())
				.originFileName(this.originFileName)
				.uploadFileName(this.uploadFileName)
				.build();
	}

	
}
