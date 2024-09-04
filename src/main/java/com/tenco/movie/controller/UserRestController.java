package com.tenco.movie.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tenco.movie.service.UserService;

import lombok.RequiredArgsConstructor;

// http://localhost:8080/api-user/check-usernam?username=유형정
@RestController
@RequestMapping("/api-user")
@RequiredArgsConstructor
public class UserRestController {
	
	private final UserService userService;

	/**
	 * 아이디 중복 검사
	 * @param loginId
	 * @return
	 * @author 형정
	 */
	@GetMapping("/check-loginId")
	public boolean getMethodName(@RequestParam(name = "loginId") String loginId) {
		System.out.println(loginId);
		boolean isUse =  userService.searchLoginId(loginId) == null ? true : false;
		System.out.println(isUse);
		return isUse;
	}
	
	/**
	 * 이메일 중복 검사
	 * @param email
	 * @return
	 * @author 형정
	 */
	@GetMapping("/check-email")
	public boolean getMethodEmail(@RequestParam(name = "email") String email) {
		System.out.println(email);
		boolean isUse =  userService.searchEmail(email) == null ? true : false;
		System.out.println(isUse);
		return isUse;
	}
	
}
