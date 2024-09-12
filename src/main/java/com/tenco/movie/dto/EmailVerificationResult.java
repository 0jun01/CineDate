package com.tenco.movie.dto;

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
public class EmailVerificationResult {

	private boolean success;
	
	// 정적 팩토리 메서드로 결과 생성
	public static EmailVerificationResult of(boolean success) {
		return new EmailVerificationResult(success);
	}
	
}
