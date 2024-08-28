package com.tenco.movie.dto;

import com.tenco.movie.repository.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class adminMemberListDTO {

	private Integer id;
	private String loginId;
	private String name;
	private String email;
	private String phoneNum;
	private String birthDay;
	private String gender;
	
	
}
