package com.tenco.movie.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.tenco.movie.dto.TossApproveResponse;
import com.tenco.movie.repository.model.User;
import com.tenco.movie.service.PaymentService;

import lombok.RequiredArgsConstructor;

@Controller 
@RequestMapping("/toss")
@RequiredArgsConstructor
public class TossController {
	
	@Autowired
	private final PaymentService servise;

	// 
	/**
	 * 토스결제 성공
	 * @author 병호
	 * @return
	 */
	@GetMapping("/success")
	public String seccese(@RequestParam(name = "orderId")String orderId,
	                      @RequestParam(name = "paymentKey")String paymentKey, 
	                      @RequestParam(name = "amount")String amount,
	                      @SessionAttribute(name = "principal")User principal)
	        throws IOException, InterruptedException {

	    System.out.println("orderId : " + orderId);
	    System.out.println("paymentKey : " + paymentKey);
	    System.out.println("amount : " + amount);

	    RestTemplate restTemplate = new RestTemplate();

	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Authorization", "Basic dGVzdF9za19lcVJHZ1lPMXI1UDdFZ0RLd05KYlZRbk4yRXlhOg=="); // basic64 << 인코딩 
	    headers.add("Content-Type", "application/json");

	    // JSON 형식의 요청 본문 생성
	    Map<String, String> requestBody = new HashMap<>();
	    requestBody.put("paymentKey", paymentKey);
	    requestBody.put("orderId", orderId);
	    requestBody.put("amount", amount);

	    HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

	    try {
	        ResponseEntity<TossApproveResponse> response = restTemplate.exchange(
	                "https://api.tosspayments.com/v1/payments/confirm",
	                HttpMethod.POST,
	                requestEntity,
	                TossApproveResponse.class);
	        
	        
	        TossApproveResponse response2 = response.getBody();
	        servise.insertTossHistory(response2, principal.getId());
	        
	        
	    } catch (HttpClientErrorException e) {
	        System.err.println("Error response body: " + e.getResponseBodyAsString());
	    }

	    return "date/datePage";
	}


	/**
	 * 토스 결제 실패 에러 처리
	 * 
	 * @return
	 */
	@GetMapping("/fail")
	public String fail() {

		return "user/signUp";
	}

	

}
