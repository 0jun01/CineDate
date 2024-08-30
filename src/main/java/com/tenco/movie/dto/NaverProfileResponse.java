package com.tenco.movie.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.tenco.movie.repository.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonNaming(value=PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class NaverProfileResponse {

	private String id;
	private String nickname;
	private String name;
	private String email;
	private String gender;
	private String age;
	private String birthday;
	private String profileImage;
	private String birthyear;
	private String mobile;
	
	public User naverUser() {
		return User.builder()
				.loginId(id)
				.name(name)
				.password(id)
				.email(email)
				.phoneNum(mobile)
				.birthDay(birthyear + "-" + birthday)
				.gender(getGenderDisplay())
				.build();
	}
	
	public String getGenderDisplay() {
        if (gender == null) {
            return "Unknown";
        }
        return gender.equals("F") ? "여" : "남";
    }
	
	
}
