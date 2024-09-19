package com.tenco.movie.repository.model;

import java.sql.Date;

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
public class UserWrite {

	private int id;
	private String loginId;
	private String name;
	private String password;
	private String email;
	private String phoneNum;
	private Date birthDay;
	private String gender;
	
}
