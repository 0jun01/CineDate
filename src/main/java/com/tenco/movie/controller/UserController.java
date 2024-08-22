package com.tenco.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tenco.bank.handler.exception.DataDeliveryException;
import com.tenco.movie.dto.SignUpDTO;
import com.tenco.movie.repository.interfaces.UserRepository;
import com.tenco.movie.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	private UserRepository userRepository;
	
	
	/**
	 * 로그인
	 * @author 성후
	 */
	@GetMapping("/signIn")
	public String signIn() {
		return "user/signIn";
	}
	/**
	 * 로그아웃
	 * @author 성후
	 */
	@GetMapping("/signUp")
	public String signUp() {
		return "user/signUp";
	}
	/**
	 * 카카오 소셜로그인
	 * @author 성후
	 */
	@GetMapping("/kakao")
	public String kakao() {
		return "redirect:/main";
	}
	/**
	 * 네이버 소셜로그인
	 * @author 성후
	 */
	@GetMapping("/naver")
	public String naver() {
		return "redirect:/main";
	}
	/**
	 * 구글 소셜로그인
	 * @author 성후
	 */
	@GetMapping("/google")
	public String google() {
		return "redirect:/main";
	}
	/**
	 * 로그아웃
	 * @author 성후
	 */
	@GetMapping("/logout")
	public String logout() {
		return "redirect:/main";
	}
	
	/**
	 * 회원가입
	 * @author 형정
	 * @return
	 */
	@PostMapping("/signUp")
	public String signUpPage(SignUpDTO dto) {
		
		System.out.println("회원가입 들어왔니?");
		
		
		if(dto.getLoginId() == null) {
			throw new DataDeliveryException("아이디를 입력해주세요.", HttpStatus.BAD_REQUEST);
		}
		if(dto.getLoginId().trim().isEmpty()) {
			throw new DataDeliveryException("아이디 공백을 제거하고 입력해주세요.", HttpStatus.BAD_REQUEST);
		}
		if(dto.getLoginId().length() > 7 || dto.getLoginId().length() > 16) {
			throw new DataDeliveryException("아이디는 6 ~ 15자까지 입니다.", HttpStatus.BAD_REQUEST);
		}
		
		if(dto.getName() == null) {
			throw new DataDeliveryException("이름을 입력해주세요.", HttpStatus.BAD_REQUEST);
		}
		if(dto.getName().trim().isEmpty()) {
			throw new DataDeliveryException("이름 공백을 제거하고 입력해주세요.", HttpStatus.BAD_REQUEST);
		}
		
		if(dto.getPassword() == null) {
			throw new DataDeliveryException("비밀번호를 입력해주세요.", HttpStatus.BAD_REQUEST);
		}
		if(dto.getPassword().trim().isEmpty()) {
			throw new DataDeliveryException("비밀번호 공백을 제거하고 입력해주세요.", HttpStatus.BAD_REQUEST);
		}
		
		if(dto.getEmail() == null) {
			throw new DataDeliveryException("이메일을 입력해주세요.", HttpStatus.BAD_REQUEST);
		}
		if(dto.getEmail().trim().isEmpty()) {
			throw new DataDeliveryException("이메일 공백을 제거하고 입력해주세요.", HttpStatus.BAD_REQUEST);
		}
		
		if(dto.getPhoneNum() == 0) {
			throw new DataDeliveryException("휴대폰 번호를 입력해주세요.", HttpStatus.BAD_REQUEST);
		}
		
	    if(dto.getBirthDay() == null || dto.getBirthMonth() == null || dto.getBirthDay() == null) {
	    	throw new DataDeliveryException("생년월일을 모두 선택해주세요.", HttpStatus.BAD_REQUEST);
	    }
	   
	    if ((dto.isGender() == false && dto.isGender() == false) || !dto.isGender()) {
	            throw new DataDeliveryException("성별을 선택해주세요.", HttpStatus.BAD_REQUEST);
	        }

	    userService.createUser(dto);
		
		return "redirect:/user/signUp";
		
	}
}
