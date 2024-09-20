package com.tenco.movie.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tenco.movie.dto.EmailVerificationResult;
import com.tenco.movie.service.MailSendService;


@Controller
@RequestMapping("/mail")
public class MailController {
	
	@Autowired
	private MailSendService mailSendService;
	
	String authCode;

	/**
	 * 이메일 인증 보내는 영역
	 * @param email
	 * @return
	 * @author 형정
	 */
	@PostMapping("/verification-requests")
	public ResponseEntity<Map<String, Object>> sendMessageProc(@RequestParam("email")String email){
		
		authCode = mailSendService.sendCodeToEmail(email);
		
		Map<String, Object> response = new HashMap<>();
		response.put("success", true);
		System.out.println("성공핸니?? 이자식아");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}

	/**	
	 * 이메일 인증 확인 영역
	 * @param email
	 * @return
	 * @author 형정
	 */
	@GetMapping("/verifications")
	public ResponseEntity<EmailVerificationResult> handleVerificationEmail(@RequestParam("email") String email, @RequestParam("code") String authCode){
		
		EmailVerificationResult result = mailSendService.verifiedCode(email, authCode);
		System.out.println("email : " + email + ", " + "authCode : " + authCode);
			
		return new ResponseEntity<>(result, HttpStatus.OK);
		
	}
	
}
