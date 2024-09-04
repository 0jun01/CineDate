package com.tenco.movie.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class TossApproveResponse {
	
	private String paymentKey; 
    private String orderId; 
    private String amount;
    
	
}
