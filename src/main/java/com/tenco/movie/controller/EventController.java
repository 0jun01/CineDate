package com.tenco.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/event")
public class EventController {

	/**
	 * 이벤트 페이지 요청
	 * @author 김가령
	 */
	@GetMapping("/event")
	public String getEventPage() {
		return "/event/eventPage";
	}
	
}
