package com.tenco.movie.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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
import com.tenco.movie.dto.TossHistoryDTO;
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
	 * 
	 * @return
	 */
	@GetMapping("/success")
	public String seccese(@RequestParam(name = "orderId") String orderId,
	                      @RequestParam(name = "paymentKey") String paymentKey, 
	                      @RequestParam(name = "amount") String amount,
	                      @SessionAttribute(name = "principal") User principal)
	        throws IOException, InterruptedException {

	    System.out.println("orderId : " + orderId);
	    System.out.println("paymentKey : " + paymentKey);
	    System.out.println("amount : " + amount);

	    RestTemplate restTemplate = new RestTemplate();

	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Authorization", "Basic dGVzdF9za19lcVJHZ1lPMXI1UDdFZ0RLd05KYlZRbk4yRXlhOg==");
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

	// 토스 결제 취소
	@GetMapping("/cancel")
	public String cancelToss() throws IOException, InterruptedException {

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://api.tosspayments.com/v1/payments/tviva20240902175023zZOP5/cancel")) //paymentKey
				// 시크릿 키를 Basic Authorization 방식으로 base64를 이용하여 인코딩하여 꼭 보내야함
				.header("Authorization", "Basic dGVzdF9za19lcVJHZ1lPMXI1UDdFZ0RLd05KYlZRbk4yRXlhOg==")
				.header("Content-Type", "application/json")
				.method("POST", HttpRequest.BodyPublishers.ofString("{\"cancelReason\":\"고객이 취소를 원함\"}")).build(); 
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());
		
		
		
		return null;
	}

}
