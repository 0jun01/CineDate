package com.tenco.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

	@GetMapping("/")
	public String getMethodName() {
		return "main";
	}
	
	@GetMapping("/movies")
	public String getMoviesPage() {
		return "main";
	}
	
	@GetMapping("/cinema")
	public String getCinemaPage() {
		return "main";
	}
	
	@GetMapping("/reservation")
	public String getReservationPage() {
		return "main";
	}
	
	@GetMapping("/event")
	public String getEventPage() {
		return "main";
	}
	
	@GetMapping("/matching")
	public String getMatchingPage() {
		return "main";
	}
}
