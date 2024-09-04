package com.tenco.movie.service;

import java.util.Calendar;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.stereotype.Service;

import com.tenco.movie.dto.TossApproveResponse;
import com.tenco.movie.dto.TossHistoryDTO;
import com.tenco.movie.repository.interfaces.PaymentHistoryRepository;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {
	
	@Autowired
	private final PaymentHistoryRepository historyRepository;
	
	
	public String getOderId() {
		Calendar cal = Calendar.getInstance();
		int y = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH) + 1;
		int d = cal.get(Calendar.DATE);
		Random rd1 =  new Random();
		Random rd2 =  new Random();
		int rd11 = rd1.nextInt(100);
		int rd22 = rd2.nextInt(100);
		
		String yStr = Integer.toString(y);
		String mStr = Integer.toString(m);
		String dStr = Integer.toString(d);
		String rd1Str = Integer.toString(rd11);
		String rd2Str = Integer.toString(rd22);
		
		return mStr + rd1Str + yStr + rd2Str + dStr;
	}
	
	public int tossHistory() {
		int resuly = 0;
		
		return resuly;
	}
	
	
	public int insertTossHistory(TossApproveResponse response, int principalId) {
		
		 int result = 0;
		
		TossHistoryDTO dto = TossHistoryDTO.builder()
				.paymentKey(response.getPaymentKey())
				.userId(principalId)
				.oderId(response.getOrderId())
				.oderName(response.getOrderName())
				.amount(response.getTotalAmount())
				.method(response.getMethod())
				.requestedAt(response.getRequestedAt())
				.approvedAt(response.getApprovedAt())
				.build();
		
		
		result = historyRepository.insertTossHistory(dto);
		
		
		return result;
	}
	
	
}
