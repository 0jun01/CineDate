package com.tenco.movie.repository.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {
	private int id; 
	private String loginId; // 로그인 할 때 ID
	private String name; // 이름
	private String password; // 비밀번호
	private String emial; // 이메일
	private int phoneNumber; // 핸드폰번호
	private Date birth; // 생년월일
	private String gender; // 성별
}
