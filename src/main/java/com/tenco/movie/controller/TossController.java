package com.tenco.movie.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.client.RestTemplate;

import com.tenco.movie.dto.TossApproveResponse;


@Controller // IoC에 대상(싱글톤 패턴으로 관리됨)
@RequestMapping("/toss")
public class TossController {

	/**
	 * 결제창 이동 처리
	 * 
	 * @return
	 */
	@GetMapping("/test")
	public String toss() {
		
		/**
		 * 파라미터로 
		 * amount 가격
		 * orderId UUID 로 6~64글자 겹치지 않게 생성
		 *  orderName 주문명
		 * customerName  유저이름
		 *  
		 * 
		 */
		
		
		
		
		// 인증검사 x
		// 유효성 검사 x
		return "TossTest";
	}

	@GetMapping("/test12")
	public String toss2() {
		// 인증검사 x
		// 유효성 검사 x
		return "checkout";
	}

	/**
	 * 토스결제 성공
	 * 
	 * @return
	 */
	@GetMapping("/success")
	public String seccese(@RequestParam(name = "orderId") String orderId,
			@RequestParam(name = "paymentKey") String paymentKey, @RequestParam(name = "amount") String amount
			)
			throws IOException, InterruptedException {
		System.out.println("orderId : " + orderId);
		System.out.println("paymentKey : " + paymentKey);
		System.out.println("amount : " + amount);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Basic dGVzdF9za19lcVJHZ1lPMXI1UDdFZ0RLd05KYlZRbk4yRXlhOg==");
        headers.set("Content-Type", "application/json");
        
        Map<String, String> parameters = new HashMap<>();
        parameters.put("paymentKey", paymentKey);
        parameters.put("orderId", orderId);
        parameters.put("amount", amount);
        
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(parameters, headers);
        
        RestTemplate restTemplate = new RestTemplate();

        TossApproveResponse approveResponse = restTemplate.postForObject(
                "https://api.tosspayments.com/v1/payments/confirm",
                requestEntity,
                TossApproveResponse.class);
        
        TossApproveResponse hitory = TossApproveResponse.builder()
        							.paymentKey(paymentKey)
        							.orderId(orderId).amount(amount)
        							.build();
        
        
        
        
		System.out.println("----------------------------- TossSeccese2 -----------------------------");
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
				.uri(URI.create("https://api.tosspayments.com/v1/payments/tviva20240828154951Pqxf5/cancel")) //paymentKey
				// 시크릿 키를 Basic Authorization 방식으로 base64를 이용하여 인코딩하여 꼭 보내야함
				.header("Authorization", "Basic dGVzdF9za19lcVJHZ1lPMXI1UDdFZ0RLd05KYlZRbk4yRXlhOg==")
				.header("Content-Type", "application/json")
				.method("POST", HttpRequest.BodyPublishers.ofString("{\"cancelReason\":\"고객이 취소를 원함\"}")).build(); 
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());
		
		
		
		return null;
	}

}
