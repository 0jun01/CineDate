package com.tenco.movie.utils;

public class MaskingUtil {

	public static String maskUserId(String userId) {
        if (userId == null || userId.length() < 4) {
            return userId; 
        }

        StringBuilder maskedId = new StringBuilder();
        int length = userId.length();
        
        maskedId.append(userId.substring(0, 2)); // 처음 두 글자 유지
        for (int i = 2; i < length - 2; i++) {
            maskedId.append('*'); // 중간 부분을 *로 마스킹
        }
        maskedId.append(userId.substring(length - 1)); // 마지막 글자 유지
        
        return maskedId.toString();
    }
	
}
