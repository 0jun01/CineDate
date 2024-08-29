package com.tenco.movie.dto;

import org.springframework.web.multipart.MultipartFile;

import com.tenco.movie.repository.model.DateProfile;

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
public class DateProfileSignUp {
	private String nickName;
	private String introduce;
	private MultipartFile mFileOne;
	private String OneOriginFileName;
	private String OneUproadFileName;
	private MultipartFile mFileTwo;
	private String TwoOriginFileName;
	private String TwoUproadFileName;
	
	public DateProfile toProfile(int PrinpalId) {
		
		
		return DateProfile.builder()
				.userId(PrinpalId)
				.nickName(nickName)
				.introduce(introduce)
				.firstOriginFileName(OneOriginFileName)
				.firstUploadFileName(OneUproadFileName)
				.secondOriginFileName(TwoOriginFileName)
				.secondUploadFileName(TwoUproadFileName)
				.build();
		
	}
	
	
	
}
