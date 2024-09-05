package com.tenco.movie.repository.model;

import org.springframework.web.multipart.MultipartFile;

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
	
	private String username;
	private String fullname;
	private MultipartFile mFile;
	private String originFileName;
	private String uploadFileName;
	
}
