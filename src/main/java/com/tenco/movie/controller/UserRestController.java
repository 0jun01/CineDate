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

	@GetMapping("/check-loginId")
	public boolean getMethodName(@RequestParam(name = "loginId") String loginId) {
		System.out.println(loginId);
		boolean isUse =  userService.searchLoginId(loginId) == null ? true : false;
		System.out.println(isUse);
		return isUse;
	}
	
	@GetMapping("/check-email")
	public boolean getMethodEmail(@RequestParam(name = "email") String email) {
		System.out.println(email);
		boolean isUse =  userService.searchEmail(email) == null ? true : false;
		System.out.println(isUse);
		return isUse;
	}
	
}
