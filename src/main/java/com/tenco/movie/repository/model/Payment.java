package com.tenco.movie.repository.model;

import java.sql.Timestamp;

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
public class Payment {

	private int id;
	private int userId; // User - pk
	private int amount; // 결제금액
	private String paymentMethod; // 결제방식
	private String paymentItem; // 구매 아이템
	private String oauthCode; // 인증코드
	private Timestamp createdAt; 
	
}
