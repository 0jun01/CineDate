package com.tenco.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

	/**
	 * 예매 페이지 요청
	 * @author 김가령
	 */
	@GetMapping("/reservation")
	public String getReservationPage() {
		return "/reservation/reservationPage";
	}
	
}
