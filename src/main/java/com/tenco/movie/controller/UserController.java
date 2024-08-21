package com.tenco.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	/**
	 * 로그인
	 * @author 성후
	 */
	@GetMapping("/signIn")
	public String signIn() {
		return "signIn";
	}
	/**
	 * 로그아웃
	 * @author 성후
	 */
	@GetMapping("/signUp")
	public String signUp() {
		return "signUp";
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
}
