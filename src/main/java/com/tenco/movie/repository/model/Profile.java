package com.tenco.movie.repository.model;

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
public class Profile {

	private int id;
	private int userId; // User - fk
	private String nickName; // 닉네임 중복 확인 기능
	private String introduce; // 자기소개
	private String firstOriginFileName; // 프로필 기존 사진 첫 번째 사진 파일명
	private String secondOriginFileName; // 프로필 두 번째 사진 파일명
	private String thirdOriginFileName; // 프로필 세 번째 사진 파일명
	private String fourthOriginFileName; // 프로필 네 번째 사진 파일명
	private String fifthOriginFileName; // 프로필 다섯 번째 사진 파일명
	private String firstUploadFileName; // 프로필 업로드 사진 첫 번째 사진 파일명
	private String secondUploadFileName; // 프로필 업로드 사진 두 번째 사진 파일명
	private int con; // 보유 콘 개수
	private int lifeStatus; // 상태 ( 0 - 계정 활성화, 1 - 계정 정지 )
	private int listStatus; // 상태 ( 0 - 일반, 1 - 슈퍼 리스트 활성화 )
	
}
