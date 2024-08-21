package com.tenco.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cinema")
public class CinemaController {

	/**
	 * 극장 페이지 요청
	 * @author 김가령
	 */
	@GetMapping("/cinema")
	public String getCinemaPage() {
		return "cinema/cinemaPage";
	}
}
