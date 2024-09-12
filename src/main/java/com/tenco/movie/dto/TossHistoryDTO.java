package com.tenco.movie.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class TossHistoryDTO {
	
	private String paymentKey;
	private int userId;
	private String oderId;
	private String oderName;
	private String amount;
	private String method;
	private String requestedAt;
	private String approvedAt;
	
}
