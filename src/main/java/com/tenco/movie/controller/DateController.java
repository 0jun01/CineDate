package com.tenco.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/date")
public class DateController {

	/**
	 * 데이트 페이지 요청
	 * @author 김가령
	 */
	@GetMapping("/date")
	public String getDatePage() {
		return "date/datePage";
	}
	
}
