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
public class CancelHistory {
	
	Integer id;
	String paymentKey;
	int userId;
	String orderId;
	String orderName;
	String amount;
	String method;
	String requestedAt;
	String approvedAt;
	String cancelAt;
	

}
